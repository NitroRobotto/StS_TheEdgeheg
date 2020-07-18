package theEdgeheg.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.SuperEdgehegPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Chaos Control 15(10). Heal 1. Gain 6 Strength, 6 Dexterity, 6 GUNS.
 * Gain 1 Energy and 1 Chaos Energy at the start of every turn.
 * NOTE: Only obtained through the Chaos Emerald questline.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-17
 */
public class SuperEdgeheg extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SuperEdgeheg.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
    public static final AbstractCard.CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int CHAOS_CONTROL_COST = 15;

    // /STAT DECLARATION/

    public SuperEdgeheg() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(EdgehegCardTags.CHAOS);
        this.tags.add(EdgehegCardTags.CHAOS_CONTROL);
        magicNumber = baseMagicNumber = CHAOS_CONTROL_COST;
        isInnate = true;
        retain = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SuperEdgehegPower(p)));
        spendChaosEnergy(p);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-5);
            initializeDescription();
        }
    }
}
