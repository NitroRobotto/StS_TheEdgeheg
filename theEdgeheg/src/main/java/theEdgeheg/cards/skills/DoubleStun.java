package theEdgeheg.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EndTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (3): Chaos Control 6. Apply Stun to all enemies. Skip the next enemy turn. End your turn. Exhaust.
 * It's Vault (Rare from Watcher) + Chaos Control (Common from Edgeheg).
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-15
 */
public class DoubleStun extends AbstractChaosControlCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Card
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(DoubleStun.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 3;
    private static final int CHAOS_ENERGY_COST = 6;
    private static final int CHAOS_ENERGY_UPGRADE = -2;

    // /STAT DECLARATION/


    public DoubleStun() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = CHAOS_ENERGY_COST;

        tags.add(EdgehegCardTags.CHAOS);
        tags.add(EdgehegCardTags.CHAOS_CONTROL);
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new StunMonsterPower(mo)));
        }
        AbstractDungeon.getCurrRoom().skipMonsterTurn = true;
        spendChaosEnergy(p);
        addToBot(new EndTurnAction());
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
