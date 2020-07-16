package theEdgeheg.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theEdgeheg.util.CardTagPredicate;

import java.util.Random;

/**
 * Draws a card with the specified Tag from the draw pile.
 * If zeroCost = true, the card will cost 0 this turn.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-16
 */
public class DrawCardWithTagAction extends AbstractGameAction {

    private final AbstractCard.CardTags targetTag;
    private final boolean zeroCost;

    public DrawCardWithTagAction(AbstractCard.CardTags targetTag, boolean zeroCost) {
        super();
        this.targetTag = targetTag;
        this.zeroCost = zeroCost;
        this.duration = 0.1f;
    }

    public DrawCardWithTagAction(AbstractCard.CardTags targetTag) {
        this(targetTag, false);
    }

    @Override
    public void update() {
        CardGroup drawPile = AbstractDungeon.player.drawPile;
        CardGroup hand = AbstractDungeon.player.hand;
        // Do nothing if there's nothing to draw/the hand is full
        if (drawPile.size() == 0 || hand.size() >= BaseMod.MAX_HAND_SIZE) { isDone = true; return; }

        if (this.duration == 0.1f) {
            Object[] cards = drawPile.group.stream().filter(new CardTagPredicate(targetTag)).toArray();

            // Do nothing if there aren't any cards with the specified tag.
            if (cards.length == 0) { isDone = true; return; }

            // Select a random card
            AbstractCard targetCard = (AbstractCard)cards[new Random().nextInt(cards.length)];
            if (zeroCost) targetCard.setCostForTurn(0);
            // Add the card to the hand (code copied from "add random card from discard action" in the base game)
            hand.addToHand(targetCard);

            targetCard.unhover();
            targetCard.setAngle(0.0F, true);
            targetCard.lighten(false);
            targetCard.drawScale = 0.12F;
            targetCard.targetDrawScale = 0.75F;
            targetCard.applyPowers();

            drawPile.removeCard(targetCard);

            hand.refreshHandLayout();
            hand.glowCheck();
        }

        tickDuration();
    }
}
