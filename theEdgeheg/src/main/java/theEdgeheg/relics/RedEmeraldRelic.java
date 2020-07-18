package theEdgeheg.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.powers.SuperEdgeheg;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

public class RedEmeraldRelic extends BaseEmeraldRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(RedEmeraldRelic.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("redEmerald.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("emeraldOutline.png"));

    public RedEmeraldRelic() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        addToBot(new AddCardToDeckAction(CardLibrary.getCard(SuperEdgeheg.ID)));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}