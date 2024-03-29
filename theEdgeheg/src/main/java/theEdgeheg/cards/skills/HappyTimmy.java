package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.attacks.guns.HappierTimmy;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.DodgePower;
import theEdgeheg.powers.GunsPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Gain 1 Dodge. Gain 1 GUNS. Add "Happier Timmy" to hand. Exhaust.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-16
 */
public class HappyTimmy extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(HappyTimmy.class.getSimpleName());
    public static final String IMG = makeCardPath("okso.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;

    // /STAT DECLARATION/


    public HappyTimmy() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DodgePower(p)));
        addToBot(new ApplyPowerAction(p, p, new GunsPower(p)));
        AbstractCard c = CardLibrary.getCard(HappierTimmy.ID).makeCopy();
        addToBot(new MakeTempCardInHandAction(c, true));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
