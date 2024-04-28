package theEdgeheg.cards.attacks.katanas;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.PreciseModifier;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (2): Gain 1d4(1d6) Chaos Energy. Deal 4+2d6(1d8+1d6) Damage. Precise. Deals bonus damage equal to Chaos Energy gathered.
 *  @author NITRO
 *  @version 1.0
 *  @since 2024-04-03
 */
public class EdgyKatana extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(EdgyKatana.class.getSimpleName());
    public static final String IMG = makeCardPath("Attacks/edgykatana.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int DAMAGE = 4;

    // /STAT DECLARATION/

    public EdgyKatana() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;

        tags.add(EdgehegCardTags.KATANA);
        tags.add(EdgehegCardTags.CHAOS);

        CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        final int chaosEnergy = upgraded ? MathUtils.random(1,6) : MathUtils.random(1,4);
        final int totalDamage = damage + chaosEnergy +
                (upgraded ? MathUtils.random(1,8) + MathUtils.random(1,6)
                        : MathUtils.random(1,6) + MathUtils.random(1,6)
                );

        addToBot(new DamageAction(m,new DamageInfo(p,totalDamage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
