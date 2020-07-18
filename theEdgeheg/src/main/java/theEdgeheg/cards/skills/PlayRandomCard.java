package theEdgeheg.cards.skills;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.util.HelperFunctions;

import java.util.Collection;
import java.util.Optional;

import static theEdgeheg.DefaultMod.makeCardPath;

public class PlayRandomCard extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(PlayRandomCard.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    // /STAT DECLARATION/


    public PlayRandomCard() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(EdgehegCardTags.CHAOS);
        exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Get a random card from every single card in the library.
        Optional<AbstractCard> optionalCard = HelperFunctions.getRandom(CardLibrary.cards.values());
        if (optionalCard.isPresent()) {
            AbstractCard card = optionalCard.get();
            BaseMod.logger.info("Playing " + card.name);
            card.exhaust = true;
            card.exhaustOnFire = true;
            addToBot(new NewQueueCardAction(card, m,true,true));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
