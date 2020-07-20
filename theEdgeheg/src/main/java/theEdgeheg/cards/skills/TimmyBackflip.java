package theEdgeheg.cards.skills;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Backflip;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.characters.TheEdgeheg;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (1): Gain 5(8) Block. Draw 2 Cards.
 * yes, it's literally just copy/pasted Backflip.
 *  @author NITRO
 *  @version 1.1
 *  @since 2020-07-16
 */
public class TimmyBackflip extends CustomCard {
    public static final String ID = DefaultMod.makeID(TimmyBackflip.class.getSimpleName());
    private static final CardStrings cardStrings;

    public TimmyBackflip() {
        super(ID, cardStrings.NAME, makeCardPath("shadow.jpg"), 1, cardStrings.DESCRIPTION, CardType.SKILL, TheEdgeheg.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new DrawCardAction(p, 2));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Backflip");
    }
}