package theEdgeheg.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.*;
import com.megacrit.cardcrawl.events.shrines.FaceTrader;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.events.BetterScrapOoze;
import theEdgeheg.events.DifferentFaceTrader;
import theEdgeheg.events.NerfedBigFish;

@SuppressWarnings("AccessStaticViaInstance")
@SpirePatch(clz=AbstractDungeon.class,method="initializeCardPools")
public class EdgehegEventsPatch {
    public static void Prefix(AbstractDungeon dungeon_instance) {
        if (AbstractDungeon.player instanceof TheEdgeheg) {
            dungeon_instance.eventList.remove(BigFish.ID);
            dungeon_instance.eventList.remove(ScrapOoze.ID);
            dungeon_instance.eventList.remove(FaceTrader.ID);
        } else {
            dungeon_instance.eventList.remove(BetterScrapOoze.ID);
            dungeon_instance.eventList.remove(NerfedBigFish.ID);
            dungeon_instance.eventList.remove(DifferentFaceTrader.ID);
        }
    }
}