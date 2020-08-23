package theEdgeheg.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.util.HelperFunctions;
import theEdgeheg.util.TextureLoader;
import theEdgeheg.util.subscribers.IOnEnemyDiedListener;

import static theEdgeheg.DefaultMod.makePowerPath;

/**
 * When a non-minion creature dies, gain 1 Dodge.
 * If "upgraded", it also triggers on minion deaths.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-12
 */
public class RuleOfCoolPower extends AbstractPower implements IOnEnemyDiedListener {

    public static final String POWER_ID = DefaultMod.makeID(RuleOfCoolPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("gun84.jpg"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("gun32.jpg"));

    private final boolean upgraded;

    public RuleOfCoolPower(AbstractCreature owner, boolean upgraded)
    {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = 1;
        this.upgraded = upgraded;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[upgraded ? 2 : 1];
    }

    @Override
    public void onEnemyDied(AbstractMonster enemy, boolean triggerRelics) {
        if (HelperFunctions.isBasicallyDead(enemy) && (upgraded || !HelperFunctions.isMinion(enemy))) {
            addToBot(new ApplyPowerAction(owner, owner, new DodgePower(owner, 1)));
        }
    }
}
