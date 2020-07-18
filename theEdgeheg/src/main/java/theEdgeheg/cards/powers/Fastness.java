package theEdgeheg.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.characters.TheEdgeheg;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Gain 4 Plated Armour. (Gain 6 Block)
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-17
 */
public class Fastness extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Fastness.class.getSimpleName());
    public static final String IMG = makeCardPath("Powers/better_than_sonic.jpg");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
    public static final AbstractCard.CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    // /STAT DECLARATION/

    public Fastness() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new PlatedArmorPower(p,4)));
        if (upgraded) addToBot(new GainBlockAction(p,p,6));
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