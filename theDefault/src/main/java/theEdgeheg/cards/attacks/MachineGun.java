package theEdgeheg.cards.attacks;

import basemod.BaseMod;
import basemod.interfaces.OnPowersModifiedSubscriber;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractDynamicCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.powers.GunsPower;

import static theEdgeheg.DefaultMod.makeCardPath;

public class MachineGun extends AbstractDynamicCard implements OnPowersModifiedSubscriber {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Deal 2(3) damage 4 times. Adds additional shots based on GUNS, but ignores Strength
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(MachineGun.class.getSimpleName());
    public static final String IMG = makeCardPath("shadow.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;
    private static final int DAMAGE = 2;
    private static final int UPGRADE_PLUS_DMG = 1;
    private static final int SHOTS = 4;

    // /STAT DECLARATION/

    public MachineGun() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = SHOTS;
        tags.add(EdgehegCardTags.GUN);

        initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        updateGunsNumber();
        BaseMod.subscribe(this);
    }

    @Override
    public void onMoveToDiscard() {
        BaseMod.unsubscribe(this);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int shotCount = magicNumber;
        for (int i = 0; i < shotCount; ++i)  {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT)
            );
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }

    public void updateGunsNumber() {
        int previousMagicNumber = magicNumber;
        magicNumber = baseMagicNumber + GunsPower.GetGunStrength(AbstractDungeon.player);

        isMagicNumberModified = magicNumber != baseMagicNumber;
        if (magicNumber != previousMagicNumber) initializeDescription();
    }

    @Override
    public void receivePowersModified() {
        updateGunsNumber();
    }
}