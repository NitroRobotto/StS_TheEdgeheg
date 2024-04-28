package theEdgeheg.cards.skills;

import theEdgeheg.DefaultMod;

import static theEdgeheg.DefaultMod.makeCardPath;

public class EvenBetterDodge extends BetterDodge {
    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(EvenBetterDodge.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/ultrainstinct.jpg");

    // /TEXT DECLARATION/

    public EvenBetterDodge() {
        super(ID, IMG, 3, CardType.SKILL, COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        magicNumber = baseMagicNumber = 4;
        secondMagicNumber = baseSecondMagicNumber = 4;

        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }
}
