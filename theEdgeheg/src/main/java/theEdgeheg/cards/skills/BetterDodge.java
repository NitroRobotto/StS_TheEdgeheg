package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.ChaosEnergyPower;
import theEdgeheg.powers.DodgePower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Gain 2(3) Dodge. Gain 2 Chaos Energy.
 *  @author NITRO
 *  @version 2.0
 *  @since 2024-04-02
 */
public class BetterDodge extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(BetterDodge.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/motorcycle.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int CHAOS_ENERGY = 2;
    private static final int BASE_DODGE = 2;
    private static final int UPGRADE_DODGE = 1;

    // /STAT DECLARATION/


    public BetterDodge() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        magicNumber = baseMagicNumber = BASE_DODGE;
        secondMagicNumber = baseSecondMagicNumber = CHAOS_ENERGY;

        initializeDescription();
    }

    public BetterDodge(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DodgePower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new ChaosEnergyPower(p, secondMagicNumber)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_DODGE);
            initializeDescription();
        }
    }
}
