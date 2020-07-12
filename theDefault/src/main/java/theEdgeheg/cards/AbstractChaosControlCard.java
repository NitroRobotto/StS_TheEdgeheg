package theEdgeheg.cards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.powers.ChaosEnergyPower;

/**
 * Cards in which the Magic Number represents an amount of Chaos
 */
public abstract class AbstractChaosControlCard extends AbstractDynamicCard {

    public AbstractChaosControlCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = hasEnoughChaosEnergy() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return hasEnoughChaosEnergy() && super.canUse(p, m);
    }

    public boolean hasEnoughChaosEnergy() {
        return magicNumber <= ChaosEnergyPower.GetChaosStrength(AbstractDungeon.player);
    }

    /**
     * Adds to the bottom of the action queue the spending of chaos energy.
     */
    protected void spendChaosEnergy(AbstractPlayer p) {addToBot(new ReducePowerAction(p, p, ChaosEnergyPower.POWER_ID, magicNumber));}
}
