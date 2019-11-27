package com.gramanicu.abilities;

public abstract class Ability {
    private final DamageType damageType;
    private final int baseDamage;
    private final int baseDamageScaling;


    public Ability(final DamageType damageType, final int baseDamage, final int baseDamageScaling) {
        this.damageType = damageType;
        this.baseDamage = baseDamage;
        this.baseDamageScaling = baseDamageScaling;
    }
}
