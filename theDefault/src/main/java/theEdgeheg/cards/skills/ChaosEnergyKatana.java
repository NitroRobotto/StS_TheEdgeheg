package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.ChaosEnergyPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Gain 1 Dexterity. Then, gain Chaos Energy = Dexterity (+2 when upgraded).
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-16
 */
public class ChaosEnergyKatana extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Gain Chaos Energy = (Current Chaos Emerald Relics) * 1(2).
     * Exhaust.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ChaosEnergyKatana.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int CHAOS_ENERGY_INCREASE = 2;

    // /STAT DECLARATION/

    public ChaosEnergyKatana() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.KATANA);
        magicNumber = baseMagicNumber = 0;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower power = p.getPower(DexterityPower.POWER_ID);
        int chaosEnergy = 1 + magicNumber + (power != null ? power.amount : 0);

        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p,1)));
        addToBot(new ApplyPowerAction(p, p, new ChaosEnergyPower(p, chaosEnergy)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(CHAOS_ENERGY_INCREASE);
            initializeDescription();
        }
    }
}
