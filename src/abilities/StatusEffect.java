package abilities;

import heroes.Hero;

public class StatusEffect {
    private static final float PERCENT = 100.0f;

    private StatusEffectType type;
    private int duration;
    private Hero target;
    private float amount;

    public StatusEffect() {
        type = StatusEffectType.NONE;
        duration = 0;
        target = null;
        amount = 0;
    }

    public StatusEffect(final Hero target, final StatusEffectType type, final int duration) {
        this.type = type;
        this.duration = duration;
        this.target = target;
    }

    public StatusEffect(final Hero target, final StatusEffectType type,
                        final float amount, final int duration) {
        this.type = type;
        this.duration = duration;
        this.target = target;
        this.amount = amount;
    }

    public StatusEffect(final Hero target, final StatusEffectType type,
                        final int amount, final int duration) {
        this.type = type;
        this.duration = duration;
        this.target = target;
        this.amount = amount;
    }

    /**
     * Apply the status effect.
     */
    public void apply() {
        duration--;
        if (type == StatusEffectType.OVERTIME_DMG
                || type == StatusEffectType.STUN_AND_DAMAGE) {
            target.takeResidualDamage(Math.round(amount));
        }

        if (duration == 0) {
            type = StatusEffectType.NONE;
            amount = 0;
        }
    }

    /**
     * Add the race bonus to the effect "amount".
     * @param raceBonus The race bonus (%)
     */
    public void setRaceBonus(final float raceBonus) {
        amount *= raceBonus;
    }

    /**
     * @return Whether or not the player can move
     */
    public boolean canMove() {
        switch (type) {
            case STUN:
            case STUN_AND_DAMAGE:
                return false;
            default:
                return true;
        }
    }
}
