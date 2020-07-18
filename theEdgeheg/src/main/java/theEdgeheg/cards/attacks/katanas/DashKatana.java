package theEdgeheg.cards.attacks.katanas;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.actions.FatalAttackAction;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.PreciseModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Deal 6(8) Precise Damage. Draw 1(2) card. If it kills, draw another card.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-17
 */
public class DashKatana extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DashKatana.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int CARD_DRAW = 1;

    // /STAT DECLARATION/

    public DashKatana() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = CARD_DRAW;

        tags.add(EdgehegCardTags.KATANA);

        CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FatalAttackAction(m, new DamageInfo(p,
                damage,
                damageTypeForTurn), () -> addToBot(new DrawCardAction(p, 1))));
        addToBot(new DrawCardAction(p, magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(CARD_DRAW);
            initializeDescription();
        }
    }
}
