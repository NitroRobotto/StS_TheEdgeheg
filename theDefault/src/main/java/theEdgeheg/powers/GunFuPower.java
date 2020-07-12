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
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makePowerPath;

/**
 * Grants +(amount) GUNS after using a Katana and +(amount) Dexterity after using a Gun.
 * Both buffs go away when the next turn starts (post-draw).
 */
public class GunFuPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID(GunFuPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("gun84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("gun32.jpg"));

    public GunFuPower(AbstractCreature owner, int amount) {
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

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);
        boolean doFlash = false;

        if (card.hasTag(EdgehegCardTags.GUN)) {
            doFlash = true;
            addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(this.owner, amount)));
            addToBot(new ApplyPowerAction(owner, owner, new LoseDexterityPower(this.owner, amount)));
        }
        if (card.hasTag(EdgehegCardTags.KATANA)) {
            doFlash = true;
            addToBot(new ApplyPowerAction(owner, owner, new GunsPower(this.owner, amount)));
            addToBot(new ApplyPowerAction(owner, owner, new LoseGunsPower(this.owner, amount)));
        }

        if (doFlash) flash();
    }
}