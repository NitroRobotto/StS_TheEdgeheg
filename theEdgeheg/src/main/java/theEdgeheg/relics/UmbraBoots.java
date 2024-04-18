package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

public class UmbraBoots extends CustomRelic {

    public static final String ID = DefaultMod.makeID(UmbraBoots.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("umbraboots.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("umbraboots.png"));

    public UmbraBoots() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        boolean doWeHaveBirbs = false;

        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster.id.equals(Byrd.ID)) {
                doWeHaveBirbs = true;
                break;
            }
        }

        if (doWeHaveBirbs) {
            AbstractCreature target = AbstractDungeon.player;
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                AbstractDungeon.getCurrRoom().smoked = true;
                this.addToBot(new VFXAction(new SmokeBombEffect(target.hb.cX, target.hb.cY)));
                AbstractDungeon.player.hideHealthBar();
                AbstractDungeon.player.isEscaping = true;
                AbstractDungeon.player.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
                AbstractDungeon.overlayMenu.endTurnButton.disable();
                AbstractDungeon.player.escapeTimer = 2.5F;
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
