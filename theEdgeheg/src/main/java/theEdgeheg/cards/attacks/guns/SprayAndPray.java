package theEdgeheg.cards.attacks.guns;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.MagicGunScalingModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Deals 1 damage 3+GUNS times to all enemies. Innate. Exhaust.
 *  @author NITRO
 *  @version 1.1
 *  @since 2024-04-15
 */
public class SprayAndPray extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SprayAndPray.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/machinegun.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 2;

    // /STAT DECLARATION/

    public SprayAndPray() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        magicNumber = baseMagicNumber = DAMAGE;
        tags.add(EdgehegCardTags.GUN);
        isMultiDamage = true;
        isInnate = true;
        exhaust = true;

        CardModifierManager.addModifier(this, new MagicGunScalingModifier( true));
        // We don't call "initializeDescription" here because addModifier already does it
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        final AbstractPower strength = p.getPower(StrengthPower.POWER_ID);
        final int shotCount = magicNumber + (strength == null ? 0 : strength.amount);

        for (int i = 0; i < shotCount; ++i)  {
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(1, true),
                    damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true)
            );
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}