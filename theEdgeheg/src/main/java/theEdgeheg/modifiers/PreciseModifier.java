package theEdgeheg.modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

/**
 * A modifier that adds the player's Dexterity power to the card's damage.
 *
 * @author NITRO
 * @version 1.0
 * @since 2020-07-09
 */
public class PreciseModifier extends AbstractCardModifier {

    private final boolean isInherent;
    private final int multiplier;

    public PreciseModifier(int multiplier, boolean isInherent) {
        this.isInherent = isInherent;
        this.multiplier = multiplier;
    }

    public PreciseModifier(boolean isInherent) {
        this(1, isInherent);
    }

    public PreciseModifier(int multiplier) {
        this(multiplier, false);
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        AbstractPower dexterityPower = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
        return damage + (dexterityPower == null ? 0 : (dexterityPower.amount*multiplier));
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return isInherent;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PreciseModifier(multiplier, isInherent);
    }
}
