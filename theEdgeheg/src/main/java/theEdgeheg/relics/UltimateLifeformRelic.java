package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import java.util.Random;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

/**
 * Reduce all damage taken to 1 (like a sort of permanent intangible). If the player is Vulnerable, the minimum damage is 2 instead.
 * At the end of the turn, has a random % of saying a random phrase.
 *  @author NITRO
 *  @version 2.0
 *  @since 2020-07-08
 */
public class UltimateLifeformRelic extends CustomRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(UltimateLifeformRelic.class.getSimpleName());
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getRelicStrings(ID).DESCRIPTIONS;

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("dusk.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("dusk.png"));

    public UltimateLifeformRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    public int onLoseHpLast(int damageAmount) {
        if (damageAmount > 1) {
            this.flash();
            return AbstractDungeon.player.hasPower(VulnerablePower.POWER_ID) ? 2 : 1;
        }

        return damageAmount;
    }

    @Override
    public void onPlayerEndTurn() {
        Random r = new Random();
        if (r.nextFloat() < 0.25f)
            addToBot(
                    new TalkAction(true,DESCRIPTIONS[r.nextInt(DESCRIPTIONS.length - 1) + 1],2.0f,2.0f)
            );
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
