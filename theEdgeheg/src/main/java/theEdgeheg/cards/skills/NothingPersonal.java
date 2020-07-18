package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (3->2) Chaos Control 4.
 * Instakill target (boss = 20% chance, elite = 40%, everyone else 100%).
 * Only spends Chaos Energy if it triggers the instakill.
 * Heal user for 1.
 * If it fails to kill the target, applies Vulnerable 3.
 *  @author NITRO
 *  @version 1.4
 *  @since 2020-07-18
 */
public class NothingPersonal extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(NothingPersonal.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/NothingPersonal.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;
    private static final int CHAOS_ENERGY_COST = 6;
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
            if (AbstractDungeon.cardRandomRng.randomBoolean(BOSS_INSTAKILL_CHANCE)) {
                addToBot(new InstantKillAction(m));
                spendChaosEnergy(p);
            } else {
                addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,3,false)));
            }
        } else if (m.type == AbstractMonster.EnemyType.ELITE) {
            if (AbstractDungeon.cardRandomRng.randomBoolean(ELITE_INSTAKILL_CHANCE)) {
                addToBot(new InstantKillAction(m));
                spendChaosEnergy(p);
            } else {
                addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,3,false)));
            }
        } else {
            addToBot(new InstantKillAction(m));
            spendChaosEnergy(p);
        }

        addToBot(new HealAction(p,p,1));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}

