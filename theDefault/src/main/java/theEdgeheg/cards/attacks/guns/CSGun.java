package theEdgeheg.cards.attacks.guns;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.DamageGunScalingModifier;
import theEdgeheg.modifiers.PreciseModifier;
import theEdgeheg.relics.CSGunRelic;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (4): Deal 50 Damage. Scales with Dexterity and GUNS (x2 both).
 * If you have the CSGunRelic, deal double damage. Can't upgrade.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-15
 */
public class CSGun extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(CSGun.class.getSimpleName());
    public static final String IMG = makeCardPath("attacks/csgun.jpg");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 4;
    private static final int DAMAGE = 50;

    // /STAT DECLARATION/

    public CSGun() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        tags.add(EdgehegCardTags.GUN);

        CardModifierManager.addModifier(this, new DamageGunScalingModifier( 2,true));
        CardModifierManager.addModifier(this, new PreciseModifier( 2,true));
        // We don't call "initializeDescription" here because addModifier already does it
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));

        addToBot(new DamageAction(m, new DamageInfo(p, damage * (p.hasRelic(CSGunRelic.ID) ? 2 : 1), damageTypeForTurn),
                AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}