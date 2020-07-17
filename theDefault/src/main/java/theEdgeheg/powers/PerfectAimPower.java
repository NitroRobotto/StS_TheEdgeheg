package theEdgeheg.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makePowerPath;

public class PerfectAimPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID(PerfectAimPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("gun84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("gun32.jpg"));

    public PerfectAimPower(AbstractCreature owner, int dexAmount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = dexAmount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onInitialApplication() {
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner,amount)));
    }

    @Override
    public void onRemove() {
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner,-amount)));
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        AbstractPower dexterity = owner.getPower(DexterityPower.POWER_ID);
        if (dexterity != null) {
            return damageAmount * (Math.random() < dexterity.amount*0.05 ? 2 : 1);
        } else {
            return damageAmount;
        }
    }
}