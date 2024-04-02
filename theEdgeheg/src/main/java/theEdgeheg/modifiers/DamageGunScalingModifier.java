package theEdgeheg.modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theEdgeheg.powers.GunsPower;

public class DamageGunScalingModifier extends AbstractCardModifier {

    private final boolean isInherent;
    private final int gunsScaling;

    public DamageGunScalingModifier(int gunsScaling, boolean isInherent) {
        this.gunsScaling = gunsScaling;
        this.isInherent = isInherent;
    }

    public DamageGunScalingModifier(boolean isInherent) {
        this(1, isInherent);
    }

    public DamageGunScalingModifier(int gunsScaling) {
        this(gunsScaling, false);
    }

    @Override
    public float modifyBaseDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage + GunsPower.GetGunStrength(AbstractDungeon.player) * gunsScaling;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DamageGunScalingModifier(gunsScaling, isInherent);
    }
}
