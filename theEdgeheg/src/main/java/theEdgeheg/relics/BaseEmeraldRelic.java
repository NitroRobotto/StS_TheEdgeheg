package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

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

    public static String GetRandomUnownedEmerald() {
        ArrayList<String> ids = new ArrayList<>();
        ids.add(BlueEmeraldRelic.ID);
        ids.add(CyanEmeraldRelic.ID);
        ids.add(GreenEmeraldRelic.ID);
        ids.add(PurpleEmeraldRelic.ID);
        ids.add(YellowEmeraldRelic.ID);
        ids.add(SilverEmeraldRelic.ID);

        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            ids.remove(relic.relicId);
        }

        if (ids.isEmpty()) {
            if (AbstractDungeon.player.hasRelic(RedEmeraldRelic.ID)) {
                return "Circlet";
            } else {
                return RedEmeraldRelic.ID;
            }
        }

        return ids.get(new Random().random(0,ids.size()-1));
    }
}
