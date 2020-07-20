package theEdgeheg.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Stuns or applies Vulnerable 2 to all enemies.
 * (50/50 against regular enemies, 25/75 against elites & bosses)
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-15
 */
public class Butt extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Butt.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/butt.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int VULNERABLE = 2;
    private static final int VULNERABLE_UPGRADE = 2;

    // /STAT DECLARATION/


    public Butt() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = VULNERABLE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (AbstractDungeon.cardRandomRng.random() <=
                    ((mo.type == AbstractMonster.EnemyType.BOSS || mo.type == AbstractMonster.EnemyType.ELITE)
                            ? 0.25f: 0.5f)) {
                addToBot(new ApplyPowerAction(mo, p, new StunMonsterPower(mo, 1)));
            } else {
                addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false)));
            }
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(VULNERABLE_UPGRADE);
            initializeDescription();
        }
    }
}
