package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Deflect extends Ability {
    private static final float PERCENT = 0.35f;
    private static final float PERCENT_SCALING = 0.02f;
    private static final float PERCENT_MAX = 0.7f;
    private static final float ROGUE_BONUS = 1.2f;
    private static final float KNIGHT_BONUS = 1.4f;
    private static final float PYROMANCER_BONUS = 1.3f;

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
        float percent = PERCENT + PERCENT_SCALING * caster.getLevel();
        percent = Math.min(percent, PERCENT_MAX);
        percent *= getTerrainBonus();
        return percent;
    }

    private int damage(final float percent) {
        float damage;
        float lastDmg = caster.getLastTotalDmg();
        ((Wizard) caster).setDamageTotal(((Wizard) caster).getDamageTotal()
                + lastDmg);
        if (((Wizard) caster).getDeflected() % 2 == 0) {
            damage = percent * ((Wizard) caster).getDamageTotal();
            ((Wizard) caster).setDamageTotal(0.0f);
            return Math.round(damage);
        } else {
            return 0;
        }
    }

    /**
     * @param target The knight to be affected by the ability
     */
    @Override
    public void affect(final Knight target) {
        float percent = attack(target);
        percent *= KNIGHT_BONUS + caster.getStatsModifier();
        int damage = damage(percent);
        target.setLastTotalDamage(damage);
        target.takeDamage(damage);
    }

    /**
     * @param target The pyromancer to be affected by the ability
     */
    @Override
    public void affect(final Pyromancer target) {
        float percent = attack(target);
        percent *= PYROMANCER_BONUS + caster.getStatsModifier();
        float damage = damage(percent);
        target.setLastTotalDamage(damage);
        target.takeDamage(Math.round(damage));
    }

    /**
     * @param target The wizard to be affected by the ability
     */
    @Override
    public void affect(final Wizard target) {
        target.setLastTotalDamage(0.0f);
        target.takeDamage(0);
    }

    /**
     * @param target The rogue to be affected by the ability
     */
    @Override
    public void affect(final Rogue target) {
        float percent = attack(target);
        percent *= ROGUE_BONUS + caster.getStatsModifier();
        int damage = damage(percent);
        target.setLastTotalDamage(damage);
        target.takeDamage(damage);
    }


}
