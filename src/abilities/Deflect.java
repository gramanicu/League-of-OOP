package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Deflect extends Ability {
    private static final int PERCENT = 35;
    private static final int PERCENT_SCALING = 2;
    private static final int PERCENT_MAX = 70;
    private static final int ROGUE_BONUS = 20;
    private static final int KNIGHT_BONUS = 40;
    private static final int PYROMANCER_BONUS = 30;;

    public Deflect(final Wizard hero) {
        caster = hero;
    }

    /**
     * Method that reduces code in visitors.
     * @param target The target to attack
     * @return The damage the target will take
     */
    @Override
    protected float attack(final Hero target) {
        int percent = PERCENT + PERCENT_SCALING * caster.getLevel();
        float damage = getPercentage(percent, caster.getLastTotalDmg());
        damage += getTerrainBonus(damage);
        ((Wizard) caster).setDamageTotal(((Wizard) caster).getDamageTotal()
                + damage);

        if (((Wizard) caster).getDeflected() % 2 == 0) {
            damage = ((Wizard) caster).getDamageTotal();
            ((Wizard) caster).setDamageTotal(0.0f);
            return damage;
        } else {
            return 0;
        }
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage += getPercentage(KNIGHT_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage += getPercentage(PYROMANCER_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        target.setLastTotalDamage(0);
        target.takeDamage(0);
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float damage = attack(target);
        target.setLastTotalDamage(Math.round(damage));
        damage += getPercentage(ROGUE_BONUS, damage);
        target.takeDamage(Math.round(damage));
    }


}
