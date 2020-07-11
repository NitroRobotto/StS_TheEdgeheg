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
 * Resource required to use cards that have "Chaos Control".
 * Use the "GetChaosStrength" function to quickly get the current amount of Chaos Energy.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-09
 */
public class ChaosEnergyPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = DefaultMod.makeID(ChaosEnergyPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("chaos84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("chaos32.jpg"));

    public ChaosEnergyPower(AbstractCreature owner, int stacks)
    {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = stacks;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.description = DESCRIPTIONS[0];
    }

    public static int GetChaosStrength(AbstractCreature owner)
    {
        AbstractPower power = owner.getPower(ChaosEnergyPower.POWER_ID);
        return power != null ? power.amount : 0;
    }

    @Override
    public AbstractPower makeCopy() {
        return new ChaosEnergyPower(owner, amount);
    }
}
