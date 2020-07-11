package theEdgeheg.cards.attacks;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.actions.BloodyKatanaAction;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.modifiers.PreciseModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Deal 10(12) damage. If it kills, heal 1.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-09
 */
public class BloodyKatana extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(BloodyKatana.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 5;
    private static final int HEAL = 1;
    private static final int UPGRADE_PLUS_HEAL = 1;

    // /STAT DECLARATION/

    public BloodyKatana() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        baseDamage = DAMAGE;
        baseMagicNumber = HEAL;
        magicNumber = baseMagicNumber;

        tags.add(EdgehegCardTags.KATANA);

       CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new BloodyKatanaAction(m, new DamageInfo(p, damage, damageTypeForTurn), 1)
        );
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_HEAL);
            initializeDescription();
        }
    }
}
