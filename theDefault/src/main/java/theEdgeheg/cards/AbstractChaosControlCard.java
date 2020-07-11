package theEdgeheg.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.powers.ChaosEnergyPower;

/**
 * Cards in which the Magic Number represents an amount of Chaos
 *
 */
public abstract class AbstractChaosControlCard extends AbstractDynamicCard {

    public AbstractChaosControlCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = canPlay(this) ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return magicNumber <= ChaosEnergyPower.GetChaosStrength(p);
    }
}
