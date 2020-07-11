package theEdgeheg.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makePowerPath;

/**
 * Equivalent to "Strength", but it only enhances cards that specifically refer to it.
 * To calculate that, you can either use the "GunScalingModifier" for that or just call GetGunStrength().
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-09
 */
public class GunsPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DefaultMod.makeID(GunsPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("gun84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("gun32.jpg"));

    public GunsPower(AbstractCreature owner, int stacks)
    {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = stacks;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public static int GetGunStrength(AbstractCreature owner)
    {
        AbstractPower power = owner.getPower(GunsPower.POWER_ID);
        return power != null ? power.amount : 0;
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new ChaosEnergyPower(owner, amount);
    }
}
