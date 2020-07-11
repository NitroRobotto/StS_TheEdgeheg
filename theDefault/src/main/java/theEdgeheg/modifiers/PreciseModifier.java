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

    public PreciseModifier(boolean isInherent) {
        this.isInherent = isInherent;
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        AbstractPower dexterityPower = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
        return damage + (dexterityPower == null ? 0 : dexterityPower.amount);
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return isInherent;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PreciseModifier(isInherent);
    }
}
