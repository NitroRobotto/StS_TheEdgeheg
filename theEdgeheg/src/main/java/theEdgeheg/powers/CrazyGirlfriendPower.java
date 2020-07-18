package theEdgeheg.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.util.TextureLoader;

import static theEdgeheg.DefaultMod.makePowerPath;

/**
 * Deal (amount) damage to a random enemy whenever you play a "GIRLFRIEND" card.
 */

public class CrazyGirlfriendPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID(CrazyGirlfriendPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("gun84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("gun32.jpg"));

    public CrazyGirlfriendPower(AbstractCreature owner, int amount) {
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
        if (card.hasTag(EdgehegCardTags.GIRLFRIEND)) {
            flash();
            addToBot(new DamageRandomEnemyAction(
                    new DamageInfo(owner,amount, DamageInfo.DamageType.HP_LOSS),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL)
            );
        }
    }
}