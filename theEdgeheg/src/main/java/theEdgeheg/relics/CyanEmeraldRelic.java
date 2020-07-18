package theEdgeheg.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theEdgeheg.DefaultMod;
import theEdgeheg.powers.ChaosEnergyPower;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

/**
 * Start combat with 5 Chaos Energy.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-17
 */
public class CyanEmeraldRelic extends BaseEmeraldRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(CyanEmeraldRelic.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("cyanEmerald.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("emeraldOutline.png"));

    public CyanEmeraldRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot(new ApplyPowerAction(
                        AbstractDungeon.player, AbstractDungeon.player,
                        new ChaosEnergyPower(AbstractDungeon.player, 5)));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}