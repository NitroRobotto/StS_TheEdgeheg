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
import theEdgeheg.modifiers.MagicGunScalingModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Deals 2(3) damage 3+GUNS times.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-11
 */
public class MachineGun extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(MachineGun.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/machinegun.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int DAMAGE = 2;
    private static final int UPGRADE_PLUS_DMG = 1;
    private static final int SHOTS = 3;

    // /STAT DECLARATION/

    public MachineGun() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = SHOTS;
        tags.add(EdgehegCardTags.GUN);
        isMultiDamage = true;

        CardModifierManager.addModifier(this, new MagicGunScalingModifier( true));
        // We don't call "initializeDescription" here because addModifier already does it
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int shotCount = magicNumber;
        for (int i = 0; i < shotCount; ++i)  {
            addToBot(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT)
            );
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