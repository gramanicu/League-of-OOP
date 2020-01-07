package heroes;

import abilities.Ability;
import abilities.StatusEffect;
import main.GreatWizard;
import maps.Map;
import maps.Movement;
import maps.Point;
import maps.TerrainType;
import strategies.NormalStrategy;
import strategies.Strategy;

import java.util.ArrayList;

public abstract class Hero {
    private final HeroType type;
    private int xp;
    private int level;
    private int hp;
    private final int hpScaling;
    private int maxHp;
    private float lastTotalDmg;
    private int id;
    private Point position;
    private StatusEffect statusEffect;
    private Strategy strategy;
    private ArrayList<Float> angelStatsModifier;

    private static final int XP_THRESHOLD = 250;
    private static final int XP_SCALING = 50;
    private static final int LVL_XP_BASE = 200;
    private static final int LVL_XP_SCALING = 40;

    public Hero(final HeroType type, final int hp, final int hpScaling, final Point position) {
        this.type = type;
        this.xp = 0;
        this.level = 0;
        this.hp = hp;
        this.hpScaling = hpScaling;
        this.maxHp = hp;
        this.position = position;
        this.lastTotalDmg = 0.0f;
        this.statusEffect = new StatusEffect();
        this.angelStatsModifier = new ArrayList<>();
    }

    /**
     * @return The id of the hero/player
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the hero/player.
     * @param id The id value
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Get affected by an ability.
     * @param ability The ability that will affect the hero
     */
    public abstract void accept(Ability ability);

    /**
     * Check if the hero is dead.
     */
    public boolean isDead() {
        return hp <= 0;
    }

    /**
     * Method used to determine what the target is.
     * @param target The hero to be attacked
     */
    public void attack(final Hero target) {
        switch (target.getType()) {
            case KNIGHT:
                attack(((Knight) target));
                break;
            case PYROMANCER:
                attack(((Pyromancer) target));
                break;
            case ROGUE:
                attack(((Rogue) target));
                break;
            case WIZARD:
                attack(((Wizard) target));
                break;
            default:
        }
    }

    /**
     * Attack another hero.
     * @param target The targeted knight
     */
    public abstract void attack(Knight target);

    /**
     * Attack another hero.
     * @param target The targeted pyromancer
     */
    public abstract void attack(Pyromancer target);

    /**
     * Attack another hero.
     * @param target The targeted wizard
     */
    public abstract void attack(Wizard target);

    /**
     * Attack another hero.
     * @param target The targeted rogue
     */
    public abstract void attack(Rogue target);

    /**
     * Take damage and return if the player is alive.
     * @param damage The damage to be taken
     * @return If the player is alive
     */
    public boolean takeDamage(final int damage) {
        hp -= damage;
        return hp > 0;
    }

    /**
     * Take damage from an angel.
     * @param damage The amount of damage
     */
    public void takeAngelDamage(final int damage) {
        hp -= damage;
        if (isDead()) {
            GreatWizard.getInstance().playerKilled(this);
        }
    }

    /**
     * Receive some hp from the angel.
     * @param amount The amount of hp
     */
    public void receiveAngelHP(final int amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    /**
     * The here will take residual damage, like the damage from pyro's ignite
     * or wizard's deflection.
     * @param damage The amount of damage
     */
    public void takeResidualDamage(final int damage) {
        hp -= damage;
    }

    /**
     * Add a status effect to the hero.
     * @param effect The effect
     */
    public void setStatusEffect(final StatusEffect effect) {
        statusEffect = effect;
    }

    /**
     * Get the amount of xp needed for level up.
     * @return The xp amount
     */
    public int getXPUntilLevel() {
        int nextLvlXP = XP_THRESHOLD + XP_SCALING * level;
        return nextLvlXP - xp;
    }

    /**
     * Level up the hero.
     */
    public void levelUP() {
        giveXP(getXPUntilLevel());
    }

    /**
     * Gives the player xp.
     * @param amount The amount of xp
     */
    public void giveXP(final int amount) {
        xp += amount;
        int oldLevel = level;

        if (xp < XP_THRESHOLD) {
            level = 0;
        } else {
            int rem = xp - XP_THRESHOLD;
            level = 0;
            while (rem >= 0) {
                rem -= XP_SCALING;
                level++;
            }
        }

        if (level != oldLevel && !isDead()) {
            resetHP();
        }

        for (int i = oldLevel + 1; i <= level; i++) {
            GreatWizard.getInstance().playerLevel(this, i);
        }
    }

    /**
     * Gives the player max hp, after computing the max hp.
     */
    protected void resetHP() {
        maxHp = getBaseHP() + getLevel() * getHpScaling();
        hp = maxHp;
    }

    /**
     * @return The starting hp of the hero
     */
    protected abstract int getBaseHP();

    /**
     * Give the winner his reward.
     * @param defeated The defeated player
     */
    public void won(final Hero defeated, final int defeatedLevel) {
        int xpToAdd = Math.max(0, LVL_XP_BASE
                - (this.getLevel() - defeatedLevel) * LVL_XP_SCALING);

        giveXP(xpToAdd);
    }

    /**
     * @return The hero type
     */
    public HeroType getType() {
        return type;
    }

    /**
     * @return The hero xp
     */
    public int getXp() {
        return xp;
    }

    /**
     * @return The hero level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return The hero current hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * @return The max hp hero gets per level
     */
    public int getHpScaling() {
        return hpScaling;
    }

    /**
     * @return The max hp of the hero
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * @return The position of the hero
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @return The terrain at the hero position
     */
    public TerrainType getTerrain() {
        return Map.getInstance().getTerrain(position);
    }

    /**
     * @return The terrain bonus for the hero
     */
    public abstract float getTerrainBonus();

    /**
     * Set the last (total) dmg the hero got (no race modifiers).
     * @param amount The dmg amount
     */
    public void setLastTotalDamage(final float amount) {
        this.lastTotalDmg = amount;
    }

    /**
     * @return The last (total) dmg the hero got
     */
    public float getLastTotalDmg() {
        return lastTotalDmg;
    }

    /**
     * @param direction Move the player in the specified direction
     */
    public void move(final Movement direction) {
        if (statusEffect.canMove()) {
            position.move(direction);
        }
        statusEffect.apply();
    }

    /**
     * @return If the player can move
     */
    public boolean canMove() {
        return statusEffect.canMove();
    }

    /**
     * Apply the hero's strategy.
     */
    public void applyStrategy(final boolean canMove) {
        if (canMove) {
            if (getHp() < ((float) getMaxHp()) / getMinStratFraction()) {
                strategy = defensiveStrategy();
            } else if (getHp() < ((float) getMaxHp()) / getMaxStratFraction()) {
                strategy = offensiveStrategy();
            } else {
                strategy = new NormalStrategy();
            }
        } else {
            strategy = new NormalStrategy();
        }
        strategy.apply();
    }

    /**
     * Kill this hero.
     */
    public void kill() {
        if (!isDead()) {
            hp = 0;
        }
    }

    /**
     * Bring this hero back to life.
     */
    public void resurrect() {
        if (isDead()) {
            hp = 1;
            GreatWizard.getInstance().playerResurrected(this);
        }
    }

    /**
     * @param baseModifier The base damage modifier
     * @return The modifier percent of the current strategy
     */
    public float getStatsModifier(final float baseModifier) {
        float stats = baseModifier;
        stats += strategy.getModifiersPerc();
        for (float stat : angelStatsModifier) {
            stats += stat;
        }
        return stats;
    }

    /**
     * Use the AttackStrategy.
     */
    protected abstract Strategy offensiveStrategy();

    /**
     * Use the DefendStrategy.
     */
    protected abstract Strategy defensiveStrategy();

    /**
     * @return The maximum treshold for strategy (as fraction denominator)
     */
    protected abstract int getMaxStratFraction();

    /**
     * @return The minimum treshold for strategy (as fraction denominator)
     */
    protected abstract int getMinStratFraction();

    /**
     * Increase the value of the angel stats modifier.
     * @param value The amount to increase
     */
    public void increaseStatsModifier(final float value) {
        angelStatsModifier.add(value);
    }

    /**
     * Modify the value of the angel stats modifier.
     * @param value The amount to decrease
     */
    public void decreaseStatsModifier(final float value) {
        angelStatsModifier.add(-value);
    }

    /**
     * @param hp Set the hp to a specific value
     */
    public void setHp(final int hp) {
        this.hp = hp;
    }

    /**
     * Returns the info about the hero.
     * @return The information
     */
    public String getStats() {
        char race;
        switch (type) {
            case KNIGHT:
                race = 'K';
                break;
            case PYROMANCER:
                race = 'P';
                break;
            case ROGUE:
                race = 'R';
                break;
            case WIZARD:
                race = 'W';
                break;
            default:
                race = ' ';
        }

        if (isDead()) {
            return String.format("%c %s", race, "dead");
        } else {
            return String.format("%c %d %d %d %d %d", race,
                    level, xp, hp, position.getY(), position.getX());
        }
    }
}
