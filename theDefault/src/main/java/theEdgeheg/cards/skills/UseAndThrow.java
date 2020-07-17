package theEdgeheg.cards.skills;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.MagicGunScalingModifier;
import theEdgeheg.powers.GunsPower;
import theEdgeheg.util.CardTagPredicate;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1->0): Select 1+X Gun cards from your hand. Play it, and then Exhaust it. Lose X Guns. Exhaust.
 * X is any number between 0 and the current amount of the GUNS buff
 *  @author NITRO
 *  @version 1.2
 *  @since 2020-07-12
 */
public class UseAndThrow extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(UseAndThrow.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");
    public static final String PROMPT = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    // /STAT DECLARATION/

    public UseAndThrow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(EdgehegCardTags.GUN);
        exhaust = true;
        magicNumber = baseMagicNumber = 1;
        CardModifierManager.addModifier(this, new MagicGunScalingModifier(1,true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(magicNumber, PROMPT, new CardTagPredicate(EdgehegCardTags.GUN), (cards) -> {
            int extraBurn = cards.size() - baseMagicNumber;
            for (AbstractCard card : cards) {
                card.exhaust = true;
                card.exhaustOnFire = true;
                addToBot(new NewQueueCardAction(card, m,true,true));
                if (extraBurn-- > 0) {
                    addToBot(new ReducePowerAction(p, p, GunsPower.POWER_ID, 1));
                }
            }
        }));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}