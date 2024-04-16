package theEdgeheg.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theEdgeheg.DefaultMod;
import theEdgeheg.cards.AbstractChaosControlCard;
import theEdgeheg.cards.EdgehegCardTags;
import theEdgeheg.characters.TheEdgeheg;
import theEdgeheg.relics.BaseEmeraldRelic;

import static theEdgeheg.DefaultMod.makeCardPath;

/**
 * (0): Chaos Control 3. Gain a random Emerald relic. Then, remove this card from your deck. Exhausts.
 *  @author NITRO
 *  @version 1.0
 *  @since 2024-04-04
 */
public class RandomEmeraldCard extends AbstractChaosControlCard {
    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(RandomEmeraldCard.class.getSimpleName());
    public static final String IMG = makeCardPath("Skills/red_emeralds.jpg");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheEdgeheg.Enums.COLOR_PURPLE;

    private static final int COST = 1;

    // /STAT DECLARATION/


    public RandomEmeraldCard() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(EdgehegCardTags.CHAOS);
        this.tags.add(EdgehegCardTags.CHAOS_CONTROL);
        magicNumber = baseMagicNumber = 3;
        exhaust = true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractRelic relic = RelicLibrary.getRelic(BaseEmeraldRelic.GetRandomUnownedEmerald());

        if (!relic.relicId.equals("Circlet")) {
            switch(relic.tier)
            {
                case COMMON:
                    RelicLibrary.commonList.remove(relic);
                    break;
                case UNCOMMON:
                    RelicLibrary.uncommonList.remove(relic);
                    break;
                case RARE:
                    RelicLibrary.rareList.remove(relic);
                    break;
                case BOSS:
                    RelicLibrary.bossList.remove(relic);
                    break;
                case SHOP:
                    RelicLibrary.shopList.remove(relic);
                    break;
                default:
                    break;
            }
        }

        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2),
                relic.makeCopy()
        );

        AbstractDungeon.player.masterDeck.removeCard(ID);
        spendChaosEnergy(abstractPlayer);
    }
}
