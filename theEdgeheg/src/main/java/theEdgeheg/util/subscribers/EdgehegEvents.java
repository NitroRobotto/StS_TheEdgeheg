package theEdgeheg.util.subscribers;

import basemod.BaseMod;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class EdgehegEvents {
    private static final ArrayList<IOnEnemyDiedSubscriber> onEnemyDiedSubscribers = new ArrayList<>();

    public static void subscribe(IOnEnemyDiedSubscriber subscriber) {
        onEnemyDiedSubscribers.add(subscriber);
    }

    public static void unsubscribe(IOnEnemyDiedSubscriber subscriber) {
        onEnemyDiedSubscribers.remove(subscriber);
    }

    public static void onEnemyDied(AbstractMonster enemy, boolean triggerRelics) {
        BaseMod.logger.info("Trigger 'On Enemy Died' on " +  onEnemyDiedSubscribers.size() + " elements");
        for (IOnEnemyDiedSubscriber item : onEnemyDiedSubscribers) {
            item.OnEnemyDied(enemy, triggerRelics);
        }
    }
}
