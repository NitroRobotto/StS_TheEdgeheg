package theEdgeheg.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.BulletStormPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Chaos Control 3. Every time you play a Gun card, gain 1(2) GUNS. After playing a non-Gun card, lose all of it.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-12
 */
public class BulletStorm extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(BulletStorm.class.getSimpleName());
    public static final String IMG = makeCardPath("Powers/bulletstorm.jpg");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
    public static final AbstractCard.CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int CHAOS_CONTROL_COST = 3;
    private static final int STACKS_PER_GUN = 1;
    private static final int STACKS_PER_GUN_UPGRADE = 1;

    // /STAT DECLARATION/

    public BulletStorm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(EdgehegCardTags.GUN);
        this.tags.add(EdgehegCardTags.CHAOS);
        this.tags.add(EdgehegCardTags.CHAOS_CONTROL);
        magicNumber = baseMagicNumber = CHAOS_CONTROL_COST;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = STACKS_PER_GUN;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BulletStormPower(p, defaultSecondMagicNumber)));
        spendChaosEnergy(p);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDefaultSecondMagicNumber(STACKS_PER_GUN_UPGRADE);
            initializeDescription();
        }
    }
}