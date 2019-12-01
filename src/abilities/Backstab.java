package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Backstab extends Ability {
    private static final int DAMAGE = 200;
    private static final int SCALING = 20;
    private static final int CRITICAL_PERIOD = 3;
    private static final int CRITICAL_PERCENT = 50;
    private static final int ROGUE_BONUS = 20;
    private static final int KNIGHT_BONUS = -10;
    private static final int PYROMANCER_BONUS = 25;
    private static final int WIZARD_BONUS = 25;

    public Backstab(final Rogue hero) {
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
        if (((Rogue) caster).getAttacksCount() % CRITICAL_PERIOD == 0) {
            if (caster.getTerrainBonus() != 0) {
                damage += getPercentage(CRITICAL_PERCENT, damage);
            }
        }
        damage += getTerrainBonus(damage);
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
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        int damage = attack(target);
        damage += getPercentage(PYROMANCER_BONUS, damage);
        target.takeDamage(damage);
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        int damage = attack(target);
        damage += getPercentage(WIZARD_BONUS, damage);
        target.takeDamage(damage);
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        int damage = attack(target);
        damage += getPercentage(ROGUE_BONUS, damage);
        target.takeDamage(damage);
    }
}
