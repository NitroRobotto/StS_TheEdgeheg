package theEdgeheg.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.util.HelperFunctions;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Chaos Control 2. Target loses 4(6) HP. Apply Vulnerable 1. % of stunning.
 * % improves when upgraded.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-16
 */
public class Glare extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Glare.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/glare.jpg");
    public static final String UPGRADE_TEXT = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int CHAOS_ENERGY_COST = 2;

    // /STAT DECLARATION/


    public Glare() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = 4;
        magicNumber = baseMagicNumber = CHAOS_ENERGY_COST;

        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.CHAOS_CONTROL);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.HP_LOSS),
                AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new ApplyPowerAction(m,p, new VulnerablePower(m,1, false)));

        if (HelperFunctions.IsMinion(m)) {
            addToBot(new ApplyPowerAction(m,p, new StunMonsterPower(m)));
        } else {
            float chance;
            switch (m.type) {
                case NORMAL:
                    chance = upgraded ? 0.99f : 0.8f;
                    break;
                case ELITE:
                    chance = upgraded ? 0.66f : 0.5f;
                    break;
                case BOSS:
                    chance = upgraded ? 0.33f : 0.2f;
                    break;
                default:
                    chance = 1.f;
                    break;
            }
            if (AbstractDungeon.cardRandomRng.randomBoolean(chance)) addToBot(new ApplyPowerAction(m,p, new StunMonsterPower(m)));
        }

        spendChaosEnergy(p);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            rawDescription += UPGRADE_TEXT;
            initializeDescription();
        }
    }
}