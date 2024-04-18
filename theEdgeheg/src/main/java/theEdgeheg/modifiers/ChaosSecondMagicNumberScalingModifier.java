package theEdgeheg.modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theEdgeheg.cards.AbstractDefaultCard;
import theEdgeheg.powers.ChaosEnergyPower;

/**
 * A modifier that overrides the SEcondMagicNumber with (baseMagicNumber + current value of the "CHAOS ENERGY" power).
 * @author NITRO
 * @version 1.0
 * @since 2024-04-18
 */
public class ChaosSecondMagicNumberScalingModifier extends AbstractCardModifier {

    private final boolean isInherent;
    private final int scaling;

    public ChaosSecondMagicNumberScalingModifier(int scaling, boolean isInherent) {
        this.scaling = scaling;
        this.isInherent = isInherent;
    }

    public ChaosSecondMagicNumberScalingModifier(boolean isInherent) {
        this(1, isInherent);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (!isInherent)
        {
            AbstractRoom room = AbstractDungeon.getCurrRoom();
            if (room != null && room.phase == AbstractRoom.RoomPhase.COMBAT) updateSecondMagicNumber(card);
        }
    }

    /**
     * Calculates and overrides the current magic number.
     * @param card Card to apply the effects to.
     * @return True if the card's magic number has changed.
     */
    public boolean updateSecondMagicNumber(AbstractCard card) {
        if (card instanceof AbstractDefaultCard)
        {
            AbstractDefaultCard castedCard = (AbstractDefaultCard) card;

            int previousMagicNumber = castedCard.secondMagicNumber;
            castedCard.secondMagicNumber = castedCard.baseSecondMagicNumber + ChaosEnergyPower.GetChaosStrength(AbstractDungeon.player) * scaling;

            castedCard.isSecondMagicNumberModified = castedCard.secondMagicNumber != castedCard.baseSecondMagicNumber;
            return castedCard.magicNumber != previousMagicNumber;
        }

        return false;
    }

    @Override
    public void onDrawn(AbstractCard card) {
        if (updateSecondMagicNumber(card)) card.initializeDescription();
    }

    @Override
    public void onApplyPowers(AbstractCard card) {
        if (updateSecondMagicNumber(card)) card.initializeDescription();
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return isInherent;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ChaosSecondMagicNumberScalingModifier(scaling, isInherent);
    }
}
