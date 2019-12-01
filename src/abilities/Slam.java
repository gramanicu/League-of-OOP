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
    protected float attack(final Hero target) {
        float damage = DAMAGE + SCALING * caster.getLevel();
        damage *= getTerrainBonus();
        statusEffect = new StatusEffect(target, StatusEffectType.STUN, STUN_DURATION);
        return damage;
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= KNIGHT_BONUS;
        target.takeDamage(Math.round(damage));
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= PYROMANCER_BONUS;
        target.takeDamage(Math.round(damage));
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= WIZARD_BONUS;
        target.takeDamage(Math.round(damage));
        target.setStatusEffect(statusEffect);
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= ROGUE_BONUS;
        target.takeDamage(Math.round(damage));
        target.setStatusEffect(statusEffect);
    }
}
