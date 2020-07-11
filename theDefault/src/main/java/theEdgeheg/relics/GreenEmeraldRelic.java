package theEdgeheg.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theEdgeheg.DefaultMod;
import theEdgeheg.powers.ChaosEnergyPower;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makeRelicOutlinePath;
import static theEdgeheg.DefaultMod.makeRelicPath;

/**
 * Gain 1 Chaos Energy at the start of each turn.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-09
 */
public class GreenEmeraldRelic extends CustomRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(GreenEmeraldRelic.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("redEmerald.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("emeraldOutline.png"));

    public GreenEmeraldRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStart() {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(
                        AbstractDungeon.player, AbstractDungeon.player,
                        new ChaosEnergyPower(AbstractDungeon.player, 1)));
    }

    public int onLoseHpLast(int damageAmount) {
        if (damageAmount > 1) {
            this.flash();
            return 1;
        }

        return damageAmount;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
