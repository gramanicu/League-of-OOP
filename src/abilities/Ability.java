package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public abstract class Ability {
    private static final float PERCENT = 100.0f;

    protected Hero caster;
    protected StatusEffect statusEffect = new StatusEffect();

    /**
     * Amplifies the damage using the terrain bonus.
     * @return The amplified damage
     */
    protected float getTerrainBonus() {
        return caster.getTerrainBonus();
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

    /**
     * @return Who casted the ability
     */
    public Hero getCaster() {
        return caster;
    }
}
