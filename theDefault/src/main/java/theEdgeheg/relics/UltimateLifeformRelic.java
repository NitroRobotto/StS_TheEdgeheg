package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

/**
 * Reduce all damage taken to 1 (like a sort of permanent intangible).
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-08
 */
public class UltimateLifeformRelic extends CustomRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(UltimateLifeformRelic.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic2.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic2.png"));

    public UltimateLifeformRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    public int onLoseHpLast(int damageAmount) {
        if (damageAmount > 1) {
            this.flash();
            return 1;
        }

        return damageAmount;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
