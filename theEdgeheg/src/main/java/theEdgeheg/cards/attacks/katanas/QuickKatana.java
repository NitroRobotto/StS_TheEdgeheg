package theEdgeheg.cards.attacks.katanas;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.actions.FatalAttackAction;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.PreciseModifier;
import theEdgeheg.powers.DodgePower;
import theEdgeheg.util.HelperFunctions;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Deal 6(8) Precise damage to all enemies. For each enemy killed, play this card again.
 *  @author NITRO
 *  @version 1.2
 *  @since 2020-07-17
 */
public class QuickKatana extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(QuickKatana.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/quickkatana.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 2;

    // /STAT DECLARATION/

    public QuickKatana() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;

        tags.add(EdgehegCardTags.KATANA);

        CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster target : AbstractDungeon.getMonsters().monsters) {
            if (!HelperFunctions.IsBasicallyDead(target) || AbstractDungeon.cardRandomRng.randomBoolean(0.1f)) {
                addToBot(new FatalAttackAction(target, new DamageInfo(p, damage, damageTypeForTurn),
                        () -> {
                        AbstractCard c = this.makeStatEquivalentCopy();
                        c.freeToPlayOnce = true;
                        c.exhaust = true;
                        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c,true,energyOnUse,
                                true,true),true);
                            })
                );
            }
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}