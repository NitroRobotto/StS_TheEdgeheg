package theEdgeheg.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.*;
import org.apache.logging.log4j.LogManager;
import theEdgeheg.DefaultMod;
import theEdgeheg.characters.TheEdgeheg;

public class RemoveUnwantedEdgehegRelicsPatch {
    @SpirePatch(clz = AbstractDungeon.class, method = "initializeRelicList")
    public static class Implementation
    {
        @SpirePrefixPatch
        public static void patch(AbstractDungeon __instance) {
            @SuppressWarnings("AccessStaticViaInstance") boolean isPlayingEdgeheg = __instance.player instanceof TheEdgeheg;

            if (isPlayingEdgeheg) {
                LogManager.getLogger(DefaultMod.class.getName()).info("Removing Edgeheg Relics");

                AbstractDungeon.relicsToRemoveOnStart.add(PrismaticShard.ID); // We don't want cards from other classes
                AbstractDungeon.relicsToRemoveOnStart.add(IncenseBurner.ID); // Edgeheg has this already.
                AbstractDungeon.relicsToRemoveOnStart.add(Torii.ID); // Also has this, but better.
                AbstractDungeon.relicsToRemoveOnStart.add(TungstenRod.ID); // Would break the character
                AbstractDungeon.relicsToRemoveOnStart.add(SingingBowl.ID); // this one can't be reconciled with
                AbstractDungeon.relicsToRemoveOnStart.add(FaceOfCleric.ID); // this one can't be reconciled with
                // TODO: Replace the Red Mask, it's useless

                // All these Relics interact with Block. They're re-allowed, even though they're probably not optimal.
                /*AbstractDungeon.relicsToRemoveOnStart.add(Anchor.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(Orichalcum.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(HornCleat.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(OrnamentalFan.ID);*/
                AbstractDungeon.relicsToRemoveOnStart.add(Calipers.ID); // we do take out the calipers because they're useless here
                /*AbstractDungeon.relicsToRemoveOnStart.add(CaptainsWheel.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(ThreadAndNeedle.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(Abacus.ID);*/

                // The Edgeheg shouldn't gain this much max HP.
                // TODO: Patch or create replacements that grant increase MaxHP by 1 instead
                AbstractDungeon.relicsToRemoveOnStart.add(Strawberry.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(DarkstonePeriapt.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(Pear.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(Mango.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(TinyHouse.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(Waffle.ID);
                // TODO: Also replace values in maxhp potion

                // Healing relics are handled by a code in The Edgeheg's "Heal" function that limits it to healing by 1 HP.
                /*AbstractDungeon.relicsToRemoveOnStart.add(EternalFeather.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(Pantograph.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(MeatOnTheBone.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(BirdFacedUrn.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(MealTicket.ID);
                AbstractDungeon.relicsToRemoveOnStart.add(BloodVial.ID);*/
                // Bloody Idol, too

            }
        }
    }
}
