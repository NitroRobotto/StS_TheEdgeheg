package theEdgeheg.cards.attacks.guns;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.DamageGunScalingModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Chaos Control 4(2). Deal 20(22) damage. Scales with Guns x2. Exhausts.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-15
 */
public class CoolExplosion extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(CoolExplosion.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/grenade.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int DAMAGE = 20;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int CHAOS_COST = 4;
    private static final int CHAOS_COST_UPGRADE = -2;

    // /STAT DECLARATION/

    public CoolExplosion() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = CHAOS_COST;
        exhaust = true;

        tags.add(EdgehegCardTags.GUN);
        tags.add(EdgehegCardTags.CHAOS_CONTROL);
        tags.add(EdgehegCardTags.CHAOS);

        CardModifierManager.addModifier(this, new DamageGunScalingModifier(2, true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(CHAOS_COST_UPGRADE);
            initializeDescription();
        }
    }
}