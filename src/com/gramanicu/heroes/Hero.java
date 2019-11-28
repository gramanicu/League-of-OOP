package com.gramanicu.heroes;

import com.gramanicu.abilities.Ability;
import com.gramanicu.abilities.StatusEffect;
import com.gramanicu.maps.Map;
import com.gramanicu.maps.Movement;
import com.gramanicu.maps.Point;
import com.gramanicu.maps.TerrainType;

public abstract class Hero {
    private final HeroType type;
    private int xp;
    private int level;
    private int hp;
    private final int hpScaling;
    private int maxHp;
    private Point position;
    private StatusEffect statusEffect;

    private static final int XP_THRESHOLD = 250;
    private static final int XP_SCALING = 50;

    public Hero(final HeroType type, final int hp, final int hpScaling, final Point position) {
        this.type = type;
        this.xp = 0;
        this.level = 0;
        this.hp = hp;
        this.hpScaling = hpScaling;
        this.maxHp = hp;
        this.position = position;
        statusEffect = new StatusEffect();
    }

    /**
     * Get affected by an ability.
     * @param ability The ability that will affect the hero
     */
    public abstract void accept(Ability ability);

    /**
     * Kills the hero.
     */
    public void kill() {
        this.hp = 0;
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
     * Gives the player xp.
     * @param amount The amount of xp
     */
    public void giveXP(final int amount) {
        xp += amount;

        level = (xp - XP_THRESHOLD) / XP_SCALING;
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
    public abstract int getTerrainBonus();

    /**
     * @param direction Move the player in the specified direction
     */
    public void move(final Movement direction) {
        if (statusEffect.canMove()) {
            position.move(direction);
        }
    }
}
