package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Slam extends Ability {
    private static final int DAMAGE = 100;
    private static final int SCALING = 40;
    private static final int PYROMANCER_BONUS = -10;
    private static final int ROGUE_BONUS = -20;
    private static final int WIZARD_BONUS = 5;
    private static final int KNIGHT_BONUS = 20;
    private static final int STUN_DURATION = 1;

    public Slam(final Knight hero) {
        caster = hero;
    }

    /**
     * Method that reduces code in visitors.
     *
     * @param target The target to attack
     * @return The damage the target will take
     */
    @Override
    protected int attack(final Hero target) {
        int damage = DAMAGE + SCALING * caster.getLevel();
        damage += getTerrainBonus(damage);
        statusEffect = new StatusEffect(target, StatusEffectType.STUN, STUN_DURATION);
        return damage;
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        int damage = attack(target);
        damage += getPercentage(KNIGHT_BONUS, damage);
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
        target.takeDamage(damage);
        target.setStatusEffect(statusEffect);
    }
}
