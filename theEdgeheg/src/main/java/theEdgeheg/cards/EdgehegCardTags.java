package theEdgeheg.cards;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Tags used in the Edgeheg cards.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-09
 */
public class EdgehegCardTags {
    @SpireEnum public static AbstractCard.CardTags GUN;
    @SpireEnum public static AbstractCard.CardTags KATANA;
    @SpireEnum public static AbstractCard.CardTags CHAOS;
    @SpireEnum public static AbstractCard.CardTags CHAOS_CONTROL;
    @SpireEnum public static AbstractCard.CardTags GIRLFRIEND;

    public static AbstractCard createRandomCardWithTag(AbstractCard.CardTags tag, AbstractCard.CardType type)
    {
        ArrayList<AbstractCard> list = new ArrayList<>();
        Iterator<AbstractCard> cardGroup = AbstractDungeon.srcCommonCardPool.group.iterator();

        AbstractCard c;
        while(cardGroup.hasNext()) {
            c = cardGroup.next();
            if (c.type == type && c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }

        cardGroup = AbstractDungeon.srcUncommonCardPool.group.iterator();

        while(cardGroup.hasNext()) {
            c = cardGroup.next();
            if (c.type == type && c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }

        cardGroup = AbstractDungeon.srcRareCardPool.group.iterator();

        while(cardGroup.hasNext()) {
            c = cardGroup.next();
            if (c.type == type && c.hasTag(tag) && !c.hasTag(AbstractCard.CardTags.HEALING)) {
                list.add(c);
            }
        }

        return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }
}
