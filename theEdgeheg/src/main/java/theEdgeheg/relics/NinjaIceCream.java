package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

public class NinjaIceCream extends CustomRelic {

    public static final String ID = DefaultMod.makeID(NinjaIceCream.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("csgun.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("csgun.png"));

    public NinjaIceCream() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }
}
