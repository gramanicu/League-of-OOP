package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Fireblast extends Ability {
    private static final int DAMAGE = 350;
    private static final int SCALING = 50;
    private static final int PYROMANCER_BONUS = -10;
    private static final int KNIGHT_BONUS = 20;
    private static final int ROGUE_BONUS = -20;
    private static final int WIZARD_BONUS = 5;

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
        damage += getTerrainBonus(damage);
        return damage;
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        damage += getPercentage(KNIGHT_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        damage += getPercentage(PYROMANCER_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        damage += getPercentage(WIZARD_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float damage = attack(target);
        target.addLastTotalDamage(Math.round(damage));
        damage += getPercentage(ROGUE_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }
}
