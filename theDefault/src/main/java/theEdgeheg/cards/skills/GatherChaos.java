package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.ChaosEnergyPower;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Gain Chaos Energy = Current Chaos Emeralds * 1(2). Exhaust.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-09
 */
public class GatherChaos extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Gain Chaos Energy = (Current Chaos Emerald Relics) * 1(2).
     * Exhaust.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(GatherChaos.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;
    private static final int CHAOS_ENERGY = 1;
    private static final int CHAOS_ENERGY_INCREASE = 1;

    // /STAT DECLARATION/

    public GatherChaos() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(EdgehegCardTags.CHAOS);
        magicNumber = baseMagicNumber = CHAOS_ENERGY;
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int chaosEnergyCount = TheEdgeheg.CountEmeralds(p) * magicNumber;

        if (chaosEnergyCount > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new ChaosEnergyPower(p, chaosEnergyCount)
                    , chaosEnergyCount)
            );
        }
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
