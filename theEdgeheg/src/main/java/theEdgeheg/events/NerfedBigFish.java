package theEdgeheg.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class NerfedBigFish extends AbstractImageEvent {
    public static final String ID = "Nerfed Big Fish";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String BANANA_RESULT;
    private static final String DONUT_RESULT;
    private static final String BOX_RESULT;
    private static final String BOX_BAD;
    private int healAmt;
    private static final int MAX_HP_AMT = 1;
    private NerfedBigFish.CurScreen screen;

    public NerfedBigFish() {
        super(NAME, DIALOG_1, "images/events/fishing.jpg");
        this.screen = NerfedBigFish.CurScreen.INTRO;
        this.healAmt = AbstractDungeon.player.maxHealth / 3;
        this.imageEventText.setDialogOption(OPTIONS[0] + this.healAmt + OPTIONS[1]);
        this.imageEventText.setDialogOption(OPTIONS[2] + 5 + OPTIONS[3]);
        this.imageEventText.setDialogOption(OPTIONS[4], CardLibrary.getCopy("Regret"));
    }

    protected void buttonEffect(int buttonPressed) {
        if (this.screen == CurScreen.INTRO) {
            switch (buttonPressed) {
                case 0:
                    AbstractDungeon.player.heal(this.healAmt, true);
                    this.imageEventText.updateBodyText(BANANA_RESULT);
                    AbstractEvent.logMetricHeal("Big Fish", "Banana", this.healAmt);
                    break;
                case 1:
                    AbstractDungeon.player.increaseMaxHp(1, true);
                    this.imageEventText.updateBodyText(DONUT_RESULT);
                    //AbstractEvent.logMetricMaxHPGain("Big Fish", "Donut", 5);
                    break;
                default:
                    this.imageEventText.updateBodyText(BOX_RESULT + BOX_BAD);
                    AbstractCard c = new Regret();
                    AbstractRelic r = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
                    AbstractEvent.logMetricObtainCardAndRelic("Big Fish", "Box", c, r);
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(CardLibrary.getCopy(c.cardID), (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), r);
            }

            this.imageEventText.clearAllDialogs();
            this.imageEventText.setDialogOption(OPTIONS[5]);
            this.screen = CurScreen.RESULT;
        } else {
            this.openMap();
        }

    }

    public void logMetric(String actionTaken) {
        AbstractEvent.logMetric("Big Fish", actionTaken);
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Big Fish");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        BANANA_RESULT = DESCRIPTIONS[1];
        DONUT_RESULT = DESCRIPTIONS[2];
        BOX_RESULT = DESCRIPTIONS[4];
        BOX_BAD = DESCRIPTIONS[5];
    }

    private enum CurScreen {
        INTRO,
        RESULT;

        CurScreen() {
        }
    }
}
