package theEdgeheg.cards.attacks.guns;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.actions.ChaosGrenadeAction;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.DamageGunScalingModifier;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Deal 5 Damage. If Fatal, stun all enemies. Damage scales with "GUNS". Upgrade: Stun target.
 *  @author NITRO
 *  @version 1.0
 *  @since 2020-07-11
 */
public class ChaosGrenade extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ChaosGrenade.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;
    private static final int DAMAGE = 5;

    // /STAT DECLARATION/

    public ChaosGrenade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        tags.add(EdgehegCardTags.GUN);
        tags.add(EdgehegCardTags.CHAOS);
        this.exhaust = true;

        CardModifierManager.addModifier(this, new DamageGunScalingModifier( true));
        // We don't call "initializeDescription" here because addModifier already does it
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChaosGrenadeAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        if (upgraded) addToBot(new ApplyPowerAction(m, p, new StunMonsterPower(m)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}