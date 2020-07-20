package theEdgeheg.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import java.util.Objects;

import static theEdgeheg.DefaultMod.makePowerPath;

/** Every point of Dexterity grants 3% of landing a critical hit for x1.5 damage. */
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

        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner,amount)));
    }

    @Override
    public void onRemove() {
        super.onRemove();
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner,-amount)));
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
        if (Objects.equals(power.ID, DexterityPower.POWER_ID)) {
            newDescription(power.amount);
        }
    }

    public void newDescription(int extraDexterity) {
        AbstractPower dexterity = owner.getPower(DexterityPower.POWER_ID);
        description = (extraDexterity+(dexterity != null ? dexterity.amount : 0))*3 + DESCRIPTIONS[0];
    }

    @Override
    public void updateDescription() {
        super.updateDescription();
        AbstractPower dexterity = owner.getPower(DexterityPower.POWER_ID);
        description = (dexterity != null ? dexterity.amount : 0)*3 + DESCRIPTIONS[0];
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        AbstractPower dexterity = owner.getPower(DexterityPower.POWER_ID);
        if (dexterity == null || (Math.random() > dexterity.amount*0.03)) {
            return damageAmount;
        } else {
            addToTop(new TalkAction(true,DESCRIPTIONS[1], 1.f,2.f));
            return Math.round(damageAmount * 1.5f);
        }
    }
}