package theEdgeheg.util;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class HelperFunctions {
    public static boolean IsBasicallyDead(AbstractCreature target) {
        return (target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower("Life Link");
    }

    public static boolean IsMinion(AbstractCreature target) {
        return target.hasPower("Minion");
    }
}
