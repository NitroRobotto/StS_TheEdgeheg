package theEdgeheg.patches.abstractMonster;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.util.subscribers.EdgehegEvents;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "die",
        paramtypez = {boolean.class}
)
public class OnEnemyDiedPatch {
    @SpirePrefixPatch
    public static void Method(AbstractMonster monster, boolean triggerRelics) {
        if (!monster.isDying) {
            EdgehegEvents.onEnemyDied(monster, triggerRelics);
        }
    }
}
