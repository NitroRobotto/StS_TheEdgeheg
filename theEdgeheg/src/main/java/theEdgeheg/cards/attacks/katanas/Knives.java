package theEdgeheg.cards.attacks.katanas;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.PreciseModifier;
import theEdgeheg.powers.ChaosEnergyPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Deal 2 Precise Damage. Gain 1 Chaos Energy. Draw 1 Card. Exhaust.
 * Upgrade: Don't exhaust.
 *  @author NITRO
 *  @version 1.2
 *  @since 2020-07-20
 */
public class Knives extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Knives.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/knives.jpg");
    public static final String UPGRADED_DESCRIPTION = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;
    private static final int DAMAGE = 2;
    private static final int CHAOS_ENERGY = 1;

    // /STAT DECLARATION/

    public Knives() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = CHAOS_ENERGY;
        exhaust = true;

        tags.add(EdgehegCardTags.KATANA);
        tags.add(EdgehegCardTags.CHAOS);

        CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(p,damage)));
        addToBot(new ApplyPowerAction(p,p, new ChaosEnergyPower(p,magicNumber)));
        addToBot(new DrawCardAction(p,1));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = UPGRADED_DESCRIPTION;
            initializeDescription();
        }
    }
}
