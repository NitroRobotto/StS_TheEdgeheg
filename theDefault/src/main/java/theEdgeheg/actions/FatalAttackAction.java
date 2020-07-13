package theEdgeheg.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class FatalAttackAction extends AbstractGameAction {
    private final Runnable callback;
    private final DamageInfo info;

    public FatalAttackAction(AbstractCreature target, DamageInfo info, Runnable fatalCallback) {
        this.info = info;
        setValues(target, info);

        callback = fatalCallback;
        actionType = ActionType.DAMAGE;
        duration = 0.1F;
    }

    public void update() {
        if (duration == 0.1F && target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.SLASH_VERTICAL));
            this.target.damage(this.info);
            if ((((AbstractMonster)target).isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower("Minion")) {
                callback.run();
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
