package theEdgeheg.cards.attacks.guns;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.DamageGunScalingModifier;
import theEdgeheg.relics.RadiationPoisoning;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (10): Retain. Costs 1 less per turn retained. Cannot be Upgraded.
 * Deals 100 Damage to All Enemies and grants the "Radiation Poisoning" relic.
 * Removed from Deck after use.
 * @author NITRO
 * @version 2.0
 * @since 2024-04-18
 */
public class UnplayableBomb extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(UnplayableBomb.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/bomb.jpg");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 10;

    // /STAT DECLARATION/

    public UnplayableBomb() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = 100;
        retain = selfRetain = true;
        exhaust = exhaustOnFire = exhaustOnUseOnce = true;

        CardModifierManager.addModifier(this, new DamageGunScalingModifier( 10, true));
        // Guns Scaling already initializes description
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractRelic relic = RelicLibrary.getRelic(RadiationPoisoning.ID);

        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2),
                relic.makeCopy()
        );

        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));

        AbstractDungeon.player.masterDeck.removeCard(ID);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void onRetained() {
        modifyCostForCombat(-1);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {

    }
}