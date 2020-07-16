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

/**
 * (1): Gain 5(8) Block. Draw 2 Cards.
 * yes, it's literally just copy/pasted Backflip.
 */
public class TimmyBackflip extends CustomCard {
    public static final String ID = "Backflip";
    private static final CardStrings cardStrings;

    public TimmyBackflip() {
        super("Backflip", cardStrings.NAME, "green/skill/backflip", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.GREEN, CardRarity.COMMON, CardTarget.SELF);
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

    public AbstractCard makeCopy() {
        return new Backflip();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Backflip");
    }
}