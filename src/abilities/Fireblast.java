package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Fireblast extends Ability {
    private static final int DAMAGE = 350;
    private static final int SCALING = 50;
    private static final float PYROMANCER_BONUS = 0.9f;
    private static final float KNIGHT_BONUS = 1.2f;
    private static final float ROGUE_BONUS = 0.8f;
    private static final float WIZARD_BONUS = 1.05f;

    public Fireblast(final Pyromancer hero) {
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
        return Math.round(damage);
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= caster.getStatsModifier(KNIGHT_BONUS);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= caster.getStatsModifier(PYROMANCER_BONUS);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= caster.getStatsModifier(WIZARD_BONUS);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage *= caster.getStatsModifier(ROGUE_BONUS);
        target.takeDamage(Math.round(damage));
    }
}
