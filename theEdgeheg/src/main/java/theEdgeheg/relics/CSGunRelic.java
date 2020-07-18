package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

/**
 * When right clicked, says a line.
 */
public class CSGunRelic extends CustomRelic implements ClickableRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(CSGunRelic.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("csgun.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("csgun.png"));

    private boolean usedThisTurn = false; // You can also have a relic be only usable once per combat. Check out Hubris for more examples, including other StSlib things.
    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.

    public CSGunRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.HEAVY);

        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void onRightClick() {
        if (!isObtained || usedThisTurn || !isPlayerTurn) {
            // If it has been used this turn, the player doesn't actually have the relic (i.e. it's on display in the shop room), or it's the enemy's turn
            return; // Don't do anything.
        }
        AbstractDungeon.actionManager.addToBottom(new TalkAction(true, DESCRIPTIONS[1], 2.0f, 2.0f));
    }

    @Override
    public void atPreBattle() {
        usedThisTurn = false; // Make sure usedThisTurn is set to false at the start of each combat.
    }

    public void atTurnStart() {
        usedThisTurn = false;  // Resets the used this turn. You can remove this to use a relic only once per combat rather than per turn.
        isPlayerTurn = true; // It's our turn!
    }

    @Override
    public void onPlayerEndTurn() {
        isPlayerTurn = false; // Not our turn now.
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
