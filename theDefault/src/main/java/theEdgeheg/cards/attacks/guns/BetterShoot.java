package theEdgeheg.cards.attacks.guns;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.DamageGunScalingModifier;
import theEdgeheg.modifiers.PreciseModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (X): Deal 6X(9X) Damage. Has Precise & scales with Guns.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-17
 */
public class BetterShoot extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(BetterShoot.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = -1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 3;

    // /STAT DECLARATION/

    public BetterShoot() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        tags.add(EdgehegCardTags.GUN);

        CardModifierManager.addModifier(this, new DamageGunScalingModifier( true));
        CardModifierManager.addModifier(this, new PreciseModifier( true));
        // We don't call "initializeDescription" here because addModifier already does it
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (energyOnUse > 0)
            addToBot(new DamageAction(m, new DamageInfo(p, damage * this.energyOnUse, damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        if (!freeToPlayOnce) {
            p.energy.use(energyOnUse);
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}