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
    private static final int PYROMANCER_BONUS = -10;
    private static final int ROGUE_BONUS = -20;
    private static final int WIZARD_BONUS = 5;
    private static final int KNIGHT_BONUS = 20;

    public Ignite(final Pyromancer hero) {
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
        int overtimeDmg = OVERTIME_BASE + OVERTIME_SCALING * caster.getLevel();
        overtimeDmg += getTerrainBonus(overtimeDmg);
        statusEffect = new StatusEffect(target,
                StatusEffectType.OVERTIME_DMG, overtimeDmg, OVERTIME_DURATION);
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
