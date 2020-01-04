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
    private static final float ROGUE_BONUS = 0.9f;
    private static final float KNIGHT_BONUS = 0.8f;
    private static final float PYROMANCER_BONUS = 1.2f;
    private static final float WIZARD_BONUS = 1.25f;

    public Paralysis(final Rogue hero) {
        caster = hero;
    }

    /**
     * Method that reduces code in visitors.
     * @param target The target to attack
     * @return The damage the target will take
     */
    @Override
    protected float attack(final Hero target) {
        float damage = DAMAGE + SCALING * caster.getLevel();
        damage *= getTerrainBonus();
        float overtimeDmg = damage;
        int duration = OVERTIME_DURATION;
        if (caster.getTerrainBonus() != 1f) {
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
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= (KNIGHT_BONUS + caster.getStrategyModifier());
        statusEffect.setRaceBonus(KNIGHT_BONUS);
        target.takeDamage(Math.round(damage));
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= PYROMANCER_BONUS;
        statusEffect.setRaceBonus(PYROMANCER_BONUS);
        target.takeDamage(Math.round(damage));
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= WIZARD_BONUS;
        statusEffect.setRaceBonus(WIZARD_BONUS);
        target.takeDamage(Math.round(damage));
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= ROGUE_BONUS;
        statusEffect.setRaceBonus(ROGUE_BONUS);
        target.takeDamage(Math.round(damage));
        target.setStatusEffect(statusEffect);
    }
}
