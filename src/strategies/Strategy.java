package strategies;

import heroes.Hero;

public interface Strategy {
    void setValues(int hpFraction, float modifiersPercent);
    void setOrigin(Hero origin);
    float getModifiersPerc();
    void apply();
}
