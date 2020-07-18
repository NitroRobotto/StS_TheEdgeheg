package theEdgeheg.util;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.function.Predicate;

public class CardTagPredicate implements Predicate<AbstractCard> {

    private AbstractCard.CardTags targetTag;

    public CardTagPredicate(AbstractCard.CardTags targetTag) {
        this.targetTag = targetTag;
    }

    @Override
    public boolean test(AbstractCard abstractCard) {
        return abstractCard.hasTag(targetTag);
    }
}
