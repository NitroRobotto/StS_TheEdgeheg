package theEdgeheg.cards.attacks.guns;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.MagicGunScalingModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Deals 1 damage 8(12)+GUNS times to random enemies.
 *  @author NITRO
 *  @version 2.1
 *  @since 2024-04-03
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
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int SHOT_COUNT = 8;
    private static final int UPGRADE_PLUS_SHOTS = 4;

    // /STAT DECLARATION/

    public MachineGun() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        tags.add(EdgehegCardTags.GUN);
        isMultiDamage = true;

        magicNumber = baseMagicNumber = SHOT_COUNT;
        CardModifierManager.addModifier(this, new MagicGunScalingModifier( true));

        //initializeDescription();
        // We don't call "initializeDescription" here because addModifier already does it
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        final AbstractPower strength = p.getPower(StrengthPower.POWER_ID);
        final int shotCount = magicNumber + (strength == null ? 0 : strength.amount);

        AbstractMonster target;
        int totalDamage;

        for (int i = 0; i < shotCount; ++i)  {
            target = AbstractDungeon.getRandomMonster();
            totalDamage = target.hasPower(VulnerablePower.POWER_ID) ? 2 : 1;

            addToBot(
                    new DamageAction(target, new DamageInfo(p, totalDamage, damageTypeForTurn),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT)
            );
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_SHOTS);
            initializeDescription();
        }
    }
}