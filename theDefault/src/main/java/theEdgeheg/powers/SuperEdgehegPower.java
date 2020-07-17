package theEdgeheg.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makePowerPath;

/**
 * Heal 1. Gain 6 Strength, 6 Dexterity, 6 GUNS. Gain 1 Energy and 1 Chaos Energy at the start of every turn.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-17
 */
public class SuperEdgehegPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID(BulletStormPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("gun84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("gun32.jpg"));

    public SuperEdgehegPower(AbstractCreature owner) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = 1;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        addToBot(new HealAction(owner,owner, 1));
        addToBot(new ApplyPowerAction(owner,owner, new StrengthPower(owner,6)));
        addToBot(new ApplyPowerAction(owner,owner, new DexterityPower(owner,6)));
        addToBot(new ApplyPowerAction(owner,owner, new GunsPower(owner,6)));
        addToBot(new ApplyPowerAction(owner,owner, new GainChaosEachTurnPower(owner,1)));
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        addToBot(new GainEnergyAction(1));
    }

    @Override
    public void onRemove() {
        super.onRemove();
        addToBot(new ReducePowerAction(owner,owner, StrengthPower.POWER_ID,6));
        addToBot(new ReducePowerAction(owner,owner, DexterityPower.POWER_ID,6));
        addToBot(new ReducePowerAction(owner,owner, GunsPower.POWER_ID,6));
        addToBot(new ReducePowerAction(owner,owner, GainChaosEachTurnPower.POWER_ID,1));
    }
}