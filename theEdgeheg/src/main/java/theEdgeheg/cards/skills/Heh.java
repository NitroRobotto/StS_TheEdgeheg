package theEdgeheg.cards.skills;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.modifiers.MagicGunScalingModifier;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Apply 2 Vulnerable (scales with GUNS). Exhaust.
 * When upgraded, doesn't exhaust.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-12
 */
public class Heh extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Heh.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/bad_palette.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 0;

    // /STAT DECLARATION/

    public Heh() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(EdgehegCardTags.GUN);
        exhaust = upgraded;
        magicNumber = baseMagicNumber = 2;
        CardModifierManager.addModifier(this, new MagicGunScalingModifier(true));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,magicNumber,false)));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}