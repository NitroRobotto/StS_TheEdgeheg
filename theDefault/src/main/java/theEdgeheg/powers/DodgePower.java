package theEdgeheg.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theEdgeheg.DefaultMod;

/**
 * Reduces the damage from the next attack to 0. Removed at the end of turn.
 * Not removed if the player has the "Superspeed" power.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-15
 */
public class DodgePower extends AbstractPower {
    public static final String POWER_ID = DefaultMod.makeID(DodgePower.class.getSimpleName());
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public DodgePower(AbstractCreature owner, int stacks) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = stacks;
        this.updateDescription();
        this.loadRegion("intangible");
        this.priority = 75;
    }

    public DodgePower(AbstractCreature owner) {
        this(owner, 1);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }

        return 0;
    }

    @Override
    public void atStartOfTurn() {
        if (!owner.hasPower(Superspeed.POWER_ID)) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

}
