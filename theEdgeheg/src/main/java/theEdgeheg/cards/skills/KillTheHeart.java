package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Kills the Heart. Otherwise, draw 1 and exhaust. Has all the tags.
 *  @author NITRO
 *  @version 1.0
 *  @since 2022-05-01
 */
public class KillTheHeart extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(KillTheHeart.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/SuperSonicSpeed.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;

    // /STAT DECLARATION/


    public KillTheHeart() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = 0;

        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.KATANA);
        tags.add(EdgehegCardTags.GIRLFRIEND);
        tags.add(EdgehegCardTags.GUN);
        tags.add(EdgehegCardTags.CHAOS_CONTROL);
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.id.equals("CorruptHeart")) {
            m.die(true);
        } else {
            addToBot(new DrawCardAction(upgraded ? 2 : 1));
        }
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