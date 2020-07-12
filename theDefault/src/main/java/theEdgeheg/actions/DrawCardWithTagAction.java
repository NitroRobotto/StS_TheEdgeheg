package theEdgeheg.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theEdgeheg.util.CardTagPredicate;

import java.util.Random;

public class DrawCardWithTagAction extends AbstractGameAction {

    private final AbstractCard.CardTags targetTag;
    private final boolean zeroCost;

    public DrawCardWithTagAction(AbstractCard.CardTags targetTag, boolean zeroCost) {
        this.targetTag = targetTag;
        this.zeroCost = zeroCost;
    }

    public DrawCardWithTagAction(AbstractCard.CardTags targetTag) {
        this(targetTag, false);
    }

    @Override
    public void update() {
        CardGroup drawPile = AbstractDungeon.player.drawPile;
        if (drawPile.size() == 0 || AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE) { isDone = true; return; }

        if (this.duration == 0.1f) {
            CardGroup hand = AbstractDungeon.player.hand;

            AbstractCard[] cards = (AbstractCard[]) drawPile.group.stream().filter(new CardTagPredicate(targetTag)).toArray();
            if (cards.length == 0) { isDone = true; return; }

            AbstractCard targetCard = cards[new Random().nextInt(cards.length)];

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
