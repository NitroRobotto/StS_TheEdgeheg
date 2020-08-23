package theEdgeheg.util.subscribers;

import basemod.BaseMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class EdgehegEvents {

    public static void onEnemyDied(AbstractMonster enemy, boolean triggerRelics) {
        BaseMod.logger.info("Trigger 'On Enemy Died' on powers");

        for (AbstractPower item : AbstractDungeon.player.powers) {
            if (item instanceof IOnEnemyDiedListener) {
                ((IOnEnemyDiedListener) item).onEnemyDied(enemy, triggerRelics);
            }
        }
    }
}
