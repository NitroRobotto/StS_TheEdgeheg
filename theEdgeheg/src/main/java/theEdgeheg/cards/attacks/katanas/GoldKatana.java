package theEdgeheg.cards.attacks.katanas;

import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.unique.GreedAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.modifiers.PreciseModifier;

import static theEdgeheg.DefaultMod.makeCardPath;


/**
 * (1): Deal 8(12) Precise damage. If it kills, gain 10(13) gold.
 * (weaker hand of greed)
 *  @author NITRO
 *  @version 1.2
 *  @since 2020-07-20
 */
public class GoldKatana extends CustomCard {
    public static final String ID = DefaultMod.makeID(GoldKatana.class.getSimpleName());
    private static final CardStrings cardStrings;

    public GoldKatana() {
        super(ID, cardStrings.NAME,  makeCardPath("Attacks/goldkatana.jpg"), 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        tags.add(EdgehegCardTags.KATANA);
        CardModifierManager.addModifier(this, new PreciseModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GreedAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
            this.upgradeMagicNumber(3);
        }

    }

    public AbstractCard makeCopy() {
        return new GoldKatana();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }
}
