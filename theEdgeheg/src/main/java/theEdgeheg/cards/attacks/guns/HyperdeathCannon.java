package theEdgeheg.cards.attacks.guns;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.ChaosSecondMagicNumberScalingModifier;
import theEdgeheg.powers.ChaosEnergyPower;

import static theEdgeheg.DefaultMod.makeCardPath;

public class HyperdeathCannon extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(HyperdeathCannon.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/supergun.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 4;
    private static final int UPGRADED_COST = 3;

    // /STAT DECLARATION/


    public HyperdeathCannon() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.CHAOS_CONTROL);
        tags.add(EdgehegCardTags.GUN);
        magicNumber = baseMagicNumber = 1;
        secondMagicNumber = baseSecondMagicNumber = 0;

        CardModifierManager.addModifier(this, new ChaosSecondMagicNumberScalingModifier( true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int remainingCost = secondMagicNumber;

        while (remainingCost > 0) {
            AbstractCard card = EdgehegCardTags.createRandomCardWithTag(EdgehegCardTags.GUN).makeCopy();
            if (card.cardID.equals(this.cardID)) {
                continue;
            }

            BaseMod.logger.info("Hyperdeath Cannon checking " + card.name);
            card.exhaust = card.exhaustOnUseOnce = card.purgeOnUse = card.exhaustOnFire = true;
            card.freeToPlayOnce = true;
            card.setCostForTurn(0);

            m = AbstractDungeon.getRandomMonster();

            BaseMod.logger.info("Hyperdeath Cannon playing " + card.name);
            remainingCost -= card.cost;
            addToBot(new NewQueueCardAction(card, m,true,true));
            addToBot(new WaitAction(0.25f));
        }

        addToBot(new RemoveSpecificPowerAction(p, p, ChaosEnergyPower.POWER_ID));
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
