package strategies;

import heroes.Hero;

public class NormalStrategy implements Strategy {
    /**
     * Set the strategy hero-specific values.
     * @param hpFraction The fraction of the hp to be changed
     * @param modifiersPercent The percent of the stats to be changed
     */
    @Override
    public void setValues(final int hpFraction, final float modifiersPercent) {
        return;
    }

    /**
     * Set the strategy owner/target.
     * @param origin The owner/target
     */
    @Override
    public void setOrigin(final Hero origin) {
        return;
    }

    /**
     * @return The stats modifier
     */
    @Override
    public float getModifiersPerc() {
        return 0f;
    }

    /**
     * Apply the strategy (change the hp).
     */
    @Override
    public void apply() {
        return;
    }
}
