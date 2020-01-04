package strategies;

import heroes.Hero;

public class DefendStrategy implements Strategy {
    private Hero origin;
    private int hpFrac;
    private float modifiersPerc;

    /**
     * Set the strategy hero-specific values.
     * @param hpFraction The fraction of the hp to be changed
     * @param modifiersPercent The percent of the stats to be changed
     */
    @Override
    public void setValues(final int hpFraction, final float modifiersPercent) {
        this.hpFrac = hpFraction;
        this.modifiersPerc = modifiersPercent;
    }

    /**
     * Set the strategy owner/target.
     * @param origin The owner/target
     */
    @Override
    public void setOrigin(final Hero origin) {
        this.origin = origin;
    }

    /**
     * @return The stats modifier
     */
    @Override
    public float getModifiersPerc() {
        return -modifiersPerc;
    }

    /**
     * Apply the strategy (change the hp).
     */
    @Override
    public void apply() {
        origin.setHp(origin.getHp() + (int) (((float) origin.getHp()) / hpFrac));
    }
}
