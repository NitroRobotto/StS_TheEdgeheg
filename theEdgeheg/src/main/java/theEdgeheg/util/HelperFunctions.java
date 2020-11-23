package theEdgeheg.util;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import theEdgeheg.DefaultMod;

import java.util.Collection;
import java.util.Optional;

public class HelperFunctions {
    public static boolean isBasicallyDead(AbstractCreature target) {
        return (target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower("Life Link");
    }

    public static boolean isMinion(AbstractCreature target) {
        return target.hasPower("Minion");
    }

    public static <E> Optional<E> getRandom (Collection<E> e) {
        return e.stream()
                .skip((int) (e.size() * AbstractDungeon.cardRandomRng.random()))
                .findFirst();
    }

    public static CardStrings GetCardString(String ID) {
        if (!DefaultMod.useEdgehegDescriptions || Math.random() < 0.069) {
            return CardCrawlGame.languagePack.getCardStrings(ID + "_Correct");
        } else {
            return CardCrawlGame.languagePack.getCardStrings(ID);
        }
    }
}
