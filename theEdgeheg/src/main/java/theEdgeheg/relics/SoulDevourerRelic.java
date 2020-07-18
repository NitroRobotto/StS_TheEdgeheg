package theEdgeheg.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

/**
 * Heal 1 after killing a non-minion enemy.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-17
 */
public class SoulDevourerRelic extends BaseEmeraldRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(SoulDevourerRelic.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public SoulDevourerRelic() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if (!m.escaped && !m.hasPower("Minion"))
        {
            flash();
            addToBot(new HealAction(AbstractDungeon.player,AbstractDungeon.player,1));
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
