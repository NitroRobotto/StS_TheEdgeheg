package theEdgeheg.cards.attacks.guns;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.MagicGunScalingModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Deal 2(3)x5* damage to the first enemy, 2(3)x3* to the second, 2(3)x1* to the third, and so on.
 *  Stops if the multiplier is 0 or below. The multiplier is increased by "GUNS".
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-13
 */
public class Shotgun extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Shotgun.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int DAMAGE = 2;
    private static final int UPGRADE_PLUS_DMG = 1;
    private static final int SHOTS = 5;

    // /STAT DECLARATION/

    public Shotgun() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = SHOTS;
        tags.add(EdgehegCardTags.GUN);

        CardModifierManager.addModifier(this, new MagicGunScalingModifier( true));
        // We don't call "initializeDescription" here because addModifier already does it
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int shotCount = magicNumber;
        for (AbstractMonster target : AbstractDungeon.getMonsters().monsters) {
            if (shotCount <= 0) break;
            addToBot(
                    new DamageAction(target, new DamageInfo(p, damage*shotCount, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.BLUNT_HEAVY)
            );
            shotCount -= 2;
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