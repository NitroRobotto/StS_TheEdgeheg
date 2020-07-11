package theEdgeheg.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.EnemyData;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.cards.skills.ChaosControlSkill;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.ChaosEnergyPower;

import static theEdgeheg.DefaultMod.makeCardPath;

public class NothingPersonal extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(NothingPersonal.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;
    private static final int CHAOS_ENERGY_COST = 4;
    private static final int CHAOS_ENERGY_COST_UPGRADE = -1;
    private static final float ELITE_INSTAKILL_CHANCE = 0.4f;
    private static final float BOSS_INSTAKILL_CHANCE = 0.2f;

    // /STAT DECLARATION/


    public NothingPersonal() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = CHAOS_ENERGY_COST;
        this.exhaust = true;

        tags.add(EdgehegCardTags.CHAOS);
        tags.add(CardTags.HEALING);
        tags.add(EdgehegCardTags.CHAOS_CONTROL);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.type == AbstractMonster.EnemyType.BOSS)  {
            if (Math.random() < BOSS_INSTAKILL_CHANCE) addToBot(new InstantKillAction(m));
        } else if (m.type == AbstractMonster.EnemyType.ELITE) {
            if (Math.random() < ELITE_INSTAKILL_CHANCE) addToBot(new InstantKillAction(m));
        } else {
            addToBot(new InstantKillAction(m));
        }

        addToBot(new HealAction(p,p,1));
        addToBot(new ReducePowerAction(p, p, ChaosEnergyPower.POWER_ID, magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            updateCost(UPGRADED_COST);
            upgradeMagicNumber(CHAOS_ENERGY_COST_UPGRADE);
            initializeDescription();
        }
    }
}

