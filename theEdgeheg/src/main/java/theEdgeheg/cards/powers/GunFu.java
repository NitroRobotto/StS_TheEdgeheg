package theEdgeheg.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.GunFuPower;
import theEdgeheg.powers.GunsPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Gain 1(2) Dexterity and 1(2) GUNS.
 * After playing a Katana or GUN card, gain 1 GUNs and 1 Dexterity respectively until the start of your next turn.
 *  @author NITRO
 *  @version 1.2
 *  @since 2020-07-17
 */
public class GunFu extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(GunFu.class.getSimpleName());
    public static final String IMG = makeCardPath("Powers/gunfu.jpg");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
    public static final AbstractCard.CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int STACKS_PER_USE = 1;
    private static final int STACKS_PER_USE_UPGRADE = 1;

    // /STAT DECLARATION/

    public GunFu() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(EdgehegCardTags.GUN);
        this.tags.add(EdgehegCardTags.KATANA);
        magicNumber = baseMagicNumber = STACKS_PER_USE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new DexterityPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p,p,new GunsPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new GunFuPower(p, 1)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(STACKS_PER_USE_UPGRADE);
            initializeDescription();
        }
    }
}