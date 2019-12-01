package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public abstract class Ability {
    private static final float PERCENT = 100.0f;

    protected static Hero caster;
    protected StatusEffect statusEffect = new StatusEffect();

    protected static float getPercentage(final int percent, final int of) {
        float res = ((float) percent) / PERCENT * ((float) of);
        return res;
    }

    protected static float getPercentage(final int percent, final float of) {
        float res = ((float) percent) / PERCENT * of;
        return res;
    }

    protected static float getPercentage(final float percent, final float of) {
        float res = percent / PERCENT * of;
        return res;
    }

    /**
     * Amplifies the damage using the terrain bonus.
     * @param damage The initial damage
     * @return The amplified damage
     */
    protected float getTerrainBonus(final float damage) {
        return getPercentage(caster.getTerrainBonus(), damage);
    }

    /**
     * Method that reduces code in visitors.
     * @param target The target to attack
     * @return The damage the target will take
     */
    protected abstract float attack(Hero target);

    /**
     * @param target The knight to be affected by the ability
     */
    public abstract void affect(Knight target);

    /**
     * @param target The pyromancer to be affected by the ability
     */
    public abstract void affect(Pyromancer target);

    /**
     * @param target The wizard to be affected by the ability
     */
    public abstract void affect(Wizard target);

    /**
     * @param target The rogue to be affected by the ability
     */
    public abstract void affect(Rogue target);
}
