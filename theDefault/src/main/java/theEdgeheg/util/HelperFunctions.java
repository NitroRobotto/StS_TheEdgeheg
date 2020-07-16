package theEdgeheg.util;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Collection;
import java.util.Optional;

public class HelperFunctions {
    public static boolean IsBasicallyDead(AbstractCreature target) {
        return (target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower("Life Link");
    }

    public static boolean IsMinion(AbstractCreature target) {
        return target.hasPower("Minion");
    }

    public static <E> Optional<E> getRandom (Collection<E> e) {
        return e.stream()
                .skip((int) (e.size() * AbstractDungeon.cardRandomRng.random()))
                .findFirst();
    }
}
