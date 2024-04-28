package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theEdgeheg.DefaultMod;
import theEdgeheg.powers.DodgePower;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

public class UltimatestLifeformRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID(UltimatestLifeformRelic.class.getSimpleName());
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getRelicStrings(ID).DESCRIPTIONS;

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ultradusk.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ultradusk.png"));

    public UltimatestLifeformRelic() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(UltimateLifeformRelic.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onLoseHp(int damageAmount) {
        final AbstractPlayer p = AbstractDungeon.player;

        if (damageAmount > 0 && p != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            flash();
            addToBot(new ApplyPowerAction(p, p, new DodgePower(p, 1)));
        }
    }
}
