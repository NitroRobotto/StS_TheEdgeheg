package theEdgeheg.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makePowerPath;

/**
 * When playing a Katana card, exhaust it and add another Katana of the same type in hand.
 */
public class EndlessKatanasPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID(EndlessKatanasPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("swordpower84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("swordpower32.jpg"));

    public EndlessKatanasPower(AbstractCreature owner) {
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
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);

        if (card.hasTag(EdgehegCardTags.KATANA))
        {
            flash();
            card.exhaustOnUseOnce = true;
            AbstractCard c = EdgehegCardTags.createRandomCardWithTag(EdgehegCardTags.KATANA, card.type).makeCopy();
            if (card.upgraded && c.canUpgrade()) c.upgrade();
            addToBot(new MakeTempCardInHandAction(c, true));
        }
    }
}