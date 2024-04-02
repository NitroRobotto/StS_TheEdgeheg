package theEdgeheg.cards.skills;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.PreciseModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Gives you 5 (9) stacks of the "Spindash" power and an Ethereal/Exhaust copy of the SpinDash card.
 * At the end of your turn, this Power will deal damage to all enemies based on the amount of stacks you have.
 * The amount of Stacks obtained is enhanced by Dexterity.
 *  @author NITRO
 *  @version 0.0
 *  @since 2024-01-11
 */
public abstract class SpinDash extends AbstractDynamicCard {
    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Reload.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/kevin.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    private static final int BASE_SPINDASH = 6;
    private static final int UPGRADE_SPINDASH = 3;

    // /STAT DECLARATION/


    public SpinDash() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        CardModifierManager.addModifier(this, new PreciseModifier( true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new ApplyPowerAction(p,p, new SpinDashPower()));
        AbstractCard c = makeStatEquivalentCopy();
        damage = BASE_SPINDASH;
        c.isEthereal = true;
        c.purgeOnUse = true;
        //PurgeField.purge.set(c, true);
        c.setCostForTurn(0);
        addToBot(new MakeTempCardInHandAction(c, true));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_SPINDASH);
            initializeDescription();
        }
    }
}
