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
 * Gain 1 Chaos Energy whenever you take Damage.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-17
 */
public class SilverEmeraldRelic extends BaseEmeraldRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(SilverEmeraldRelic.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("silverEmerald.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("emeraldOutline.png"));

    public SilverEmeraldRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onLoseHp(int damageAmount) {
        if (damageAmount > 0) {
            flash();
            addToBot(new ApplyPowerAction(
                    AbstractDungeon.player, AbstractDungeon.player,
                    new ChaosEnergyPower(AbstractDungeon.player, 1)));
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}