package theEdgeheg.cards.attacks.katanas;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.actions.FatalAttackAction;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.PreciseModifier;
import theEdgeheg.powers.ChaosEnergyPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Gain 1 Chaos Energy. Deal 6(9) Damage. Precise. If Fatal, gain 1 Chaos Energy.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-13
 */
public class ChaosKatana extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ChaosKatana.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 3;

    // /STAT DECLARATION/

    public ChaosKatana() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;

        tags.add(EdgehegCardTags.KATANA);
        tags.add(EdgehegCardTags.CHAOS);

        CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new ChaosEnergyPower(p,1)));
        addToBot(new FatalAttackAction(m,
                new DamageInfo(p, damage, damageTypeForTurn),
                () -> addToTop(new ApplyPowerAction(p,p, new ChaosEnergyPower(p,1)))));
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
