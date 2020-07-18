package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.GunsPower;
import theEdgeheg.powers.LoseGunsPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Adds 2(4) GUNS until the end of the turn.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-12
 */
public class Reload extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Reload.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/reload.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;
    private static final int BONUS_GUNS = 2;
    private static final int BONUS_GUNS_UPGRADE = 2;


    // /STAT DECLARATION/


    public Reload() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        magicNumber = baseMagicNumber = BONUS_GUNS;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GunsPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseGunsPower(p, magicNumber)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(BONUS_GUNS_UPGRADE);
            initializeDescription();
        }
    }
}
