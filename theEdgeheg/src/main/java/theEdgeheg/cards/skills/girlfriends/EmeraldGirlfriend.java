package theEdgeheg.cards.skills.girlfriends;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.ChaosEnergyPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Draw 1 Card. Gain 1(2) Chaos Energy.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-20
 */
public class EmeraldGirlfriend extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(EmeraldGirlfriend.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/chaosgirlfriend.jpg");
    public static final CardStrings STRINGS = languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;

    // /STAT DECLARATION/


    public EmeraldGirlfriend() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.GIRLFRIEND);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p,1));
        addToBot(new ApplyPowerAction(p, p, new ChaosEnergyPower(p,magicNumber)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
