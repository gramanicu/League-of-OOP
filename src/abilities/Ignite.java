package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Ignite extends Ability {
    private static final int DAMAGE = 150;
    private static final int SCALING = 20;
    private static final int OVERTIME_BASE = 50;
    private static final int OVERTIME_SCALING = 30;
    private static final int OVERTIME_DURATION = 2;
    private static final float PYROMANCER_BONUS = 0.9f;
    private static final float ROGUE_BONUS = 0.8f;
    private static final float WIZARD_BONUS = 1.05f;
    private static final float KNIGHT_BONUS = 1.2f;

    public Ignite(final Pyromancer hero) {
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
        int overtimeDmg = OVERTIME_BASE + OVERTIME_SCALING * caster.getLevel();
        overtimeDmg *= getTerrainBonus();
        statusEffect = new StatusEffect(target,
                StatusEffectType.OVERTIME_DMG, overtimeDmg, OVERTIME_DURATION);
        return damage;
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.setLastTotalDamage(damage);
        damage *= KNIGHT_BONUS;
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
