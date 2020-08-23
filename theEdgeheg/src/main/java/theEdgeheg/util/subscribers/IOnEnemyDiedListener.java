package theEdgeheg.util.subscribers;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface IOnEnemyDiedListener {
    void onEnemyDied(AbstractMonster enemy, boolean triggerRelics);
}
