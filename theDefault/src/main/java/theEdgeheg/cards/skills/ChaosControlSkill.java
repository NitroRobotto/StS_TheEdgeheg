package theEdgeheg.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
 * (1): Chaos Control (5->4). Apply Stun to all enemies.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-09
 */
public class ChaosControlSkill extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ChaosControlSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int CHAOS_ENERGY_COST = 5;
    private static final int CHAOS_ENERGY_UPGRADE = -1;

    // /STAT DECLARATION/


    public ChaosControlSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = CHAOS_ENERGY_COST;
        magicNumber = baseMagicNumber;

        tags.add(EdgehegCardTags.CHAOS);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = canPlay(this) ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return magicNumber <= ChaosEnergyPower.GetChaosStrength(p);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p,
                    new StunMonsterPower(mo, 1)));
        }
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, ChaosEnergyPower.POWER_ID, magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(CHAOS_ENERGY_UPGRADE);
            initializeDescription();
        }
    }
}
