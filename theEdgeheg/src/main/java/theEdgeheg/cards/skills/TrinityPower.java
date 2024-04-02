package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.ChaosEnergyPower;
import theEdgeheg.powers.GunsPower;
import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Gain 1(2) GUNS. Gain 1(2) DEXTERITY. Gain 2(4) CHAOS ENERGY.
 *  @author NITRO
 *  @version 1.0
 *  @since 2024-04-02
 */
public class TrinityPower extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(TrinityPower.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/chaos_emeralds.jpg");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;

    public TrinityPower() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.KATANA);
        tags.add(EdgehegCardTags.GUN);
        magicNumber = baseMagicNumber = 1;
        secondMagicNumber = baseSecondMagicNumber = 2;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GunsPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new ChaosEnergyPower(p, secondMagicNumber)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeSecondMagicNumber(2);
            initializeDescription();
        }
    }
}
