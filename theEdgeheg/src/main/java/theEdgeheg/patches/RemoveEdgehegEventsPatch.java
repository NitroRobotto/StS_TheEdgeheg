package theEdgeheg.patches;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theEdgeheg.characters.TheEdgeheg;

//@SpirePatch(clz=AbstractDungeon.class,method="initializeCardPools")
public class RemoveEdgehegEventsPatch {
    public static void Prefix(AbstractDungeon dungeon_instance) {
        //if (AbstractDungeon.player instanceof TheEdgeheg) {
            //dungeon_instance.eventList.remove(Event.ID);
        //}
    }
}