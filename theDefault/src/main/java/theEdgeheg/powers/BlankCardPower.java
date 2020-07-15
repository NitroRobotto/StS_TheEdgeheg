package theEdgeheg.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makePowerPath;

/**
 * Gain Dexterity per card played. Lose it at the start of your next turn.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-15
 */
public class BlankCardPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID(BlankCardPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("gun84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("gun32.jpg"));

    public BlankCardPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.description = DESCRIPTIONS[0];
    }

    public BlankCardPower(AbstractCreature owner) {
        this(owner,1);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);
        flash();
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(this.owner, amount)));
        addToBot(new ApplyPowerAction(owner, owner, new LoseDexterityPower(this.owner, amount)));
    }
}