package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Paralysis extends Ability {
    private static final int DAMAGE = 40;
    private static final int SCALING = 10;
    private static final int OVERTIME_DURATION = 3;
    private static final int OVERTIME_BONUS = 6;
    private static final int ROGUE_BONUS = -10;
    private static final int KNIGHT_BONUS = -20;
    private static final int PYROMANCER_BONUS = 20;
    private static final int WIZARD_BONUS = 25;

    public Paralysis(final Rogue hero) {
        caster = hero;
    }

    /**
     * Method that reduces code in visitors.
     * @param target The target to attack
     * @return The damage the target will take
     */
    @Override
    protected int attack(final Hero target) {
        int damage = DAMAGE + SCALING * caster.getLevel();
        damage += getTerrainBonus(damage);
        int overtimeDmg = damage;
        int duration = OVERTIME_DURATION;
        if (caster.getTerrainBonus() != 0) {
            duration = OVERTIME_BONUS;
        }

        statusEffect = new StatusEffect(target,
                StatusEffectType.STUN_AND_DAMAGE, overtimeDmg, duration);
        return damage;
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        int damage = attack(target);
        damage += getPercentage(KNIGHT_BONUS, damage);
        statusEffect.setRaceBonus(KNIGHT_BONUS);
        target.takeDamage(damage);
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        int damage = attack(target);
        damage += getPercentage(PYROMANCER_BONUS, damage);
        statusEffect.setRaceBonus(PYROMANCER_BONUS);
        target.takeDamage(damage);
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        int damage = attack(target);
        damage += getPercentage(WIZARD_BONUS, damage);
        statusEffect.setRaceBonus(WIZARD_BONUS);
        target.takeDamage(damage);
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        int damage = attack(target);
        damage += getPercentage(ROGUE_BONUS, damage);
        statusEffect.setRaceBonus(ROGUE_BONUS);
        target.takeDamage(damage);
        target.setStatusEffect(statusEffect);
    }
}
