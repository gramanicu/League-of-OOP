package com.gramanicu.abilities;

import com.gramanicu.heroes.Hero;

public class StatusEffect {
    private StatusEffectType type;
    private int duration;
    private Hero target;
    private int amount;

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
        if (type == StatusEffectType.OVERTIME_DMG) {
            target.takeResidualDamage(amount);
        }

        if (duration == 0) {
            type = StatusEffectType.NONE;
            amount = 0;
        }
    }

    /**
     * @return Whether or not the player can move
     */
    public boolean canMove() {
        return type == StatusEffectType.STUN;
    }
}
