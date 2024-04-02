package theEdgeheg.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.ChaosEnergyPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Chaos Control 2. Deal 4 + X damage, where X = Chaos Energy when casting. Upgrade: Remove Chaos Control.
 *  @author NITRO
 *  @version 2.0
 *  @since 2024-04-02
 */
public class ChaosBlast extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ChaosBlast.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/chaosblast.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int CHAOS_ENERGY_COST = 2;
    private static final int BASE_DAMAGE = 4;

    // /STAT DECLARATION/


    public ChaosBlast() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = CHAOS_ENERGY_COST;
        damage = baseDamage = BASE_DAMAGE;

        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.CHAOS_CONTROL);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage + ChaosEnergyPower.GetChaosStrength(p),
                damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));

        spendChaosEnergy(p);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-CHAOS_ENERGY_COST);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            tags.remove(EdgehegCardTags.CHAOS_CONTROL);
            initializeDescription();
        }
    }
}