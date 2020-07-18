package theEdgeheg.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.RuleOfCoolPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1) -> When a non-minion creature dies, gain 1 Dodge.
 * If "upgraded", it also triggers on minion deaths.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-12
 */
public class RuleOfCool extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Gain 3(5) GUNS.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(RuleOfCool.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    // /STAT DECLARATION/

    public RuleOfCool() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(EdgehegCardTags.KATANA);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RuleOfCoolPower(p, upgraded)));
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