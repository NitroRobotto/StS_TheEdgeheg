package theEdgeheg.cards.attacks.katanas;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.actions.FatalAttackAction;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.PreciseModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Deal 7(10) Precise Damage. Gain 2 Dexterity this turn. Precise. If Fatal, gain 1 Strength.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-20
 */
public class QuickDraw extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(QuickDraw.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/quickdraw.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;

    // /STAT DECLARATION/

    public QuickDraw() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;

        tags.add(EdgehegCardTags.KATANA);

        CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FatalAttackAction(m,
                new DamageInfo(p, damage, damageTypeForTurn),
                () -> addToBot(new ApplyPowerAction(p,p, new StrengthPower(p,1)))));
        addToBot(new ApplyPowerAction(p,p, new DexterityPower(p,2)));
        addToBot(new ApplyPowerAction(p,p, new LoseDexterityPower(p,2)));
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
