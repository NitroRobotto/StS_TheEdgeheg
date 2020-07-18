package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class BaseEmeraldRelic extends CustomRelic {
    public BaseEmeraldRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }

    public BaseEmeraldRelic(String id, Texture texture, Texture outline, RelicTier tier, LandingSound sfx) {
        super(id, texture, outline, tier, sfx);
    }

    public BaseEmeraldRelic(String id, String imgName, RelicTier tier, LandingSound sfx) {
        super(id, imgName, tier, sfx);
    }

    public static int CountEmeralds()
    {
        int relicCount = 0;
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            if (relic instanceof BaseEmeraldRelic) ++relicCount;
        }

        return relicCount;
    }
}
