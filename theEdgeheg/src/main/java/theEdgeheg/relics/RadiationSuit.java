package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.attacks.guns.UnplayableBomb;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

public class RadiationSuit extends CustomRelic {

    public static final String ID = DefaultMod.makeID(RadiationSuit.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("radsuit.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("radsuit.png"));

    public RadiationSuit() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.HEAVY);
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.masterDeck.findCardById(UnplayableBomb.ID) != null;
    }
}
