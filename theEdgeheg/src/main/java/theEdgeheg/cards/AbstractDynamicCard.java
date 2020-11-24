package theEdgeheg.cards;

import com.megacrit.cardcrawl.localization.CardStrings;
import theEdgeheg.util.HelperFunctions;

public abstract class AbstractDynamicCard extends AbstractDefaultCard {

    // "How come StarterShootAttack extends CustomCard and not DynamicCard like all the rest?"

    // Well every card, at the end of the day, extends CustomCard.
    // Abstract Default Card extends CustomCard and builds up on it, adding a second magic number. Your card can extend it and
    // bam - you can have a second magic number in that card (Learn Java inheritance if you want to know how that works).
    // Abstract Dynamic Card builds up on Abstract Default Card even more and makes it so that you don't need to add
    // the NAME and the DESCRIPTION into your card - it'll get it automatically. Of course, this functionality could have easily
    // Been added to the default card rather than creating a new Dynamic one, but was done so to deliberately.

    protected CardStrings cardStrings;

    public AbstractDynamicCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {
        super(id, "", img, cost, "", type, color, rarity, target);

        cardStrings = HelperFunctions.GetCardString(id);

        originalName = name = cardStrings.NAME;
        rawDescription = cardStrings.DESCRIPTION;

        initializeTitle();
        initializeDescription();
    }
}