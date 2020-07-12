package theEdgeheg.util.subscribers;

import basemod.interfaces.ISubscriber;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface IOnEnemyDiedSubscriber extends ISubscriber {
    void OnEnemyDied(AbstractMonster enemy, boolean triggerRelics);
}
