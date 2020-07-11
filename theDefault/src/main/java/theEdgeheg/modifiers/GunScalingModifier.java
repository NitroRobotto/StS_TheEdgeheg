package theEdgeheg.modifiers;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theEdgeheg.powers.GunsPower;

/**
 * A modifier that overrides the MagicNumber with (baseMagicNumber + current value of the "GUNS" power).
 * @author NITRO
 * @version 1.0
 * @since 2020-07-11
 */
public class GunScalingModifier extends AbstractCardModifier {

    private final boolean isInherent;
    private final int gunsScaling;

    public GunScalingModifier(int gunsScaling, boolean isInherent) {
        this.gunsScaling = gunsScaling;
        this.isInherent = isInherent;
    }

    public GunScalingModifier(boolean isInherent) {
        this(1, isInherent);
    }

    public GunScalingModifier(int gunsScaling) {
        this(gunsScaling, false);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (!isInherent)
        {
            AbstractRoom room = AbstractDungeon.getCurrRoom();
            if (room != null && room.phase == AbstractRoom.RoomPhase.COMBAT) updateMagicNumber(card);
        }
    }

    /**
     * Calculates and overrides the current magic number.
     * @param card Card to apply the effects to.
     * @return True if the card's magic number has changed.
     */
    public boolean updateMagicNumber(AbstractCard card) {

        int previousMagicNumber = card.magicNumber;
        card.magicNumber = card.baseMagicNumber + GunsPower.GetGunStrength(AbstractDungeon.player) * gunsScaling;

        card.isMagicNumberModified = card.magicNumber != card.baseMagicNumber;
        return card.magicNumber != previousMagicNumber;
    }

    @Override
    public void onDrawn(AbstractCard card) {
        if (updateMagicNumber(card)) card.initializeDescription();
    }

    @Override
    public void onApplyPowers(AbstractCard card) {
        if (updateMagicNumber(card)) card.initializeDescription();
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return isInherent;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GunScalingModifier(gunsScaling, isInherent);
    }
}
