package theEdgeheg.events;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import theEdgeheg.relics.BaseEmeraldRelic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DifferentFaceTrader extends AbstractImageEvent {
    public static final String ID = "DifferentFaceTrader";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static int goldReward;
    private static int damage;
    private DifferentFaceTrader.CurScreen screen;

    public DifferentFaceTrader() {
        super(NAME, DESCRIPTIONS[0], "images/events/facelessTrader.jpg");
        this.screen = DifferentFaceTrader.CurScreen.INTRO;
        if (AbstractDungeon.ascensionLevel >= 15) {
            goldReward = 50;
        } else {
            goldReward = 75;
        }

        damage = AbstractDungeon.player.maxHealth / 10;
        if (damage == 0) {
            damage = 1;
        }

        this.imageEventText.setDialogOption(OPTIONS[4]);
    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screen) {
            case INTRO:
                if (buttonPressed == 0) {
                    this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                    this.imageEventText.updateDialogOption(0, OPTIONS[0] + damage + OPTIONS[5] + goldReward + OPTIONS[1]);
                    this.imageEventText.setDialogOption(OPTIONS[2]);
                    this.imageEventText.setDialogOption(OPTIONS[3]);
                    this.screen = CurScreen.MAIN;
                    return;
                }
                return;
            case MAIN:
                switch(buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        AbstractDungeon.effectList.add(new RainingGoldEffect(goldReward));
                        AbstractDungeon.player.gainGold(goldReward);
                        AbstractDungeon.player.damage(new DamageInfo(null, damage));
                        CardCrawlGame.sound.play("ATTACK_POISON");
                        break;
                    case 1:
                        AbstractRelic r = this.getRandomFace();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, r);
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                }

                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[3]);
                this.screen = DifferentFaceTrader.CurScreen.RESULT;
                break;
            default:
                this.openMap();
        }

    }

    private AbstractRelic getRandomFace() {
        ArrayList<String> ids = new ArrayList<>();
        if (!AbstractDungeon.player.hasRelic("CultistMask")) {
            ids.add("CultistMask");
        }

        if (!AbstractDungeon.player.hasRelic("GremlinMask")) {
            ids.add("GremlinMask");
        }

        if (!AbstractDungeon.player.hasRelic("NlothsMask")) {
            ids.add("NlothsMask");
        }

        if (!AbstractDungeon.player.hasRelic("SsserpentHead")) {
            ids.add("SsserpentHead");
        }

        ids.add(BaseEmeraldRelic.GetRandomUnownedEmerald());

        if (ids.size() <= 0) {
            ids.add("Circlet");
        }

        Collections.shuffle(ids, new Random(AbstractDungeon.miscRng.randomLong()));
        return RelicLibrary.getRelic(ids.get(0)).makeCopy();
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("FaceTrader");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
    }

    private enum CurScreen {
        INTRO,
        MAIN,
        RESULT;

        CurScreen() {
        }
    }
}
