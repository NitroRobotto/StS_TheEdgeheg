package theEdgeheg.cards.attacks.katanas;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.PreciseModifier;
import theEdgeheg.powers.ChaosEnergyPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Deal 5(7) precise damage to 2 random enemies. Gain 1(2) Chaos Energy.
 *  @author NITRO
 *  @version 1.2
 *  @since 2020-07-20
 */
public class ChaosWhip extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ChaosWhip.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/chaoswhip.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    // /STAT DECLARATION/

    public ChaosWhip() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = 5;
        magicNumber = baseMagicNumber = 1;

        tags.add(EdgehegCardTags.KATANA);
        tags.add(EdgehegCardTags.CHAOS);
        isMultiDamage = true;

        CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; ++i) {
            addToBot(new DamageAction(AbstractDungeon.getRandomMonster(),
                    new DamageInfo(p,damage,damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

        addToBot(new ApplyPowerAction(p,p, new ChaosEnergyPower(p,magicNumber)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
