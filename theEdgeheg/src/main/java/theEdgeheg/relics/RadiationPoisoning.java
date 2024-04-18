package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

/**
 * Until the next Boss, all non-elite enemies take 100 damage at the start of combat, but you take 10.
 * There's a 50% chance that you won't get rewards out of this combat.
 */
public class RadiationPoisoning extends CustomRelic {

    public static final String ID = DefaultMod.makeID(RadiationPoisoning.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("radiation.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("radiation.png"));

    public RadiationPoisoning() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (m.type == AbstractMonster.EnemyType.ELITE) {
                return;
            } else if (m.type == AbstractMonster.EnemyType.BOSS) {
                AbstractDungeon.player.loseRelic(ID);
                return;
            }
        }

        if (MathUtils.randomBoolean()) {
            AbstractDungeon.getCurrRoom().rewardAllowed = false;
        }

        addToBot(
                new DamageAction(AbstractDungeon.player,
                new DamageInfo(AbstractDungeon.player, 10),
                AbstractGameAction.AttackEffect.FIRE)
        );
        addToBot(
                new DamageAllEnemiesAction(
                        AbstractDungeon.player,
                        100, DamageInfo.DamageType.NORMAL,
                        AbstractGameAction.AttackEffect.FIRE
                )
        );
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
