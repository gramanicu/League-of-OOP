package angels;

import heroes.Hero;
import main.GreatWizard;
import maps.Point;

public abstract class Angel {
    private final AngelType type;
    private Point position;

    protected Angel(final AngelType type, final Point position) {
        this.type = type;
        this.position = position;
    }

    public abstract void apply(Hero target);

    /**
     * Print the text for a buff.
     * @param target The hero to be buffed
     */
    protected void buffText(final Hero target) {
        GreatWizard.getInstance().angelHelp(this.getType(), target.getType(), target.getId());
    }

    /**
     * Print the text for a debuff.
     * @param target The hero to be debuffed
     */
    protected void debuffText(final Hero target) {
        GreatWizard.getInstance().angelHit(this.getType(), target.getType(), target.getId());
    }

    /**
     * Print the text for a angel kill.
     * @param target The hero to be killed
     */
    protected void killText(final Hero target) {
        GreatWizard.getInstance().playerKilled(target);
    }

    /**
     * @return The type of the angel (as a AngelType)
     */
    public AngelType getType() {
        return type;
    }

    /**
     * @return The position of the angel
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Kill the specified hero.
     * @param target The killed hero
     */
    protected void kill(final Hero target) {
        target.kill();
    }

    /**
     * Bring the hero back to life.
     * @param target The resurrected hero
     */
    protected void resurrect(final Hero target) {
        target.resurrect();
    }

    /**
     * Set the hp to a specified value.
     * @param target The hero affected
     * @param value The new hp
     */
    protected void setHP(final Hero target, final int value) {
        target.setHp(value);
    }

    /**
     * Add a value to the current hp of the hero.
     * Negative values are allowed
     * @param target The hero affected
     * @param value The amount to change
     */
    protected void modifyHP(final Hero target, final int value) {
        target.setHp(target.getHp() + value);
    }

    /**
     * Give experience points to the specified hero.
     * @param target The affected hero
     * @param value The amount of xp to be added
     */
    protected void giveXP(final Hero target, final int value) {
        target.giveXP(value);
    }

    /**
     * Add a value to the stats modifier of the specified target.
     * Negative values are allowed
     * @param target The target of the buff/debuff
     * @param value The value of the buff/debuff
     */
    protected void modifyStats(final Hero target, final int value) {
        target.increaseStatsModifier(value);
    }

    /**
     * Level up the hero.
     * @param target The hero that will level up
     */
    protected void levelUP(final Hero target) {

    }

}
