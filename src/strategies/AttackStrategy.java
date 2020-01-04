package strategies;

import heroes.Hero;

public class AttackStrategy implements Strategy {
    private Hero owner;
    private int hpFract;
    private float modifiersPerc;

    /**
     * Set the strategy hero-specific values.
     * @param hpFraction The fraction of the hp to be changed
     * @param modifiersPercent The percent of the stats to be changed
     */
    @Override
    public void setValues(final int hpFraction, final float modifiersPercent) {
        this.hpFract = hpFraction;
        this.modifiersPerc = modifiersPercent;
    }

    /**
     * Set the strategy owner/target.
     * @param origin The owner/target
     */
    @Override
    public void setOrigin(final Hero origin) {
        this.owner = origin;
    }

    /**
     * @return The stats modifier
     */
    @Override
    public float getModifiersPerc() {
        return modifiersPerc;
    }

    /**
     * Apply the strategy (change the hp).
     */
    @Override
    public void apply() {
        owner.setHp(owner.getHp() - (int) (((float) owner.getHp()) / hpFract));
    }
}
