package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.TeleportBehindPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Chaos Control 2. The next card is played 2(3) times.
 *  @author NITRO
 *  @version 1.2
 *  @since 2020-07-18
 */
public class TeleportsBehind extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(TeleportsBehind.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/teleportsbehind.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int CHAOS_ENERGY_COST = 3;

    // /STAT DECLARATION/


    public TeleportsBehind() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = CHAOS_ENERGY_COST;
        secondMagicNumber = baseSecondMagicNumber = 2;

        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.CHAOS_CONTROL);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new TeleportBehindPower(p, secondMagicNumber-1)));
        spendChaosEnergy(p);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeSecondMagicNumber(1);
            initializeDescription();
        }
    }
}
