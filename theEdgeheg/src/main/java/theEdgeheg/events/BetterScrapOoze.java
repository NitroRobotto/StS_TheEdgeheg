package theEdgeheg.events;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterScrapOoze extends AbstractImageEvent {
    private static final Logger logger = LogManager.getLogger(com.megacrit.cardcrawl.events.exordium.ScrapOoze.class.getName());
    public static final String ID = "Better Scrap Ooze";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private int relicObtainChance = 33;
    private int dmg = 1;
    private int totalDamageDealt = 0;
    private int screenNum = 0;
    private static final String DIALOG_1;
    private static final String FAIL_MSG;
    private static final String SUCCESS_MSG;
    private static final String ESCAPE_MSG;

    public BetterScrapOoze() {
        super(NAME, DIALOG_1, "images/events/scrapOoze.jpg");
        this.imageEventText.setDialogOption(OPTIONS[0] + this.dmg + OPTIONS[1] + this.relicObtainChance + OPTIONS[2]);
        this.imageEventText.setDialogOption(OPTIONS[3]);
    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_OOZE");
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screenNum) {
            case 0:
                switch(buttonPressed) {
                    case 0:
                        AbstractDungeon.player.damage(new DamageInfo(null, this.dmg));
                        CardCrawlGame.sound.play("ATTACK_POISON");
                        this.totalDamageDealt += this.dmg;
                        int random = AbstractDungeon.miscRng.random(0, 99);
                        if (random >= 99 - this.relicObtainChance) {
                            this.imageEventText.updateBodyText(SUCCESS_MSG);
                            AbstractRelic r = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
                            this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                            this.imageEventText.removeDialogOption(1);
                            this.screenNum = 1;
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F, r);
                        } else {
                            this.imageEventText.updateBodyText(FAIL_MSG);
                            this.relicObtainChance += 33;
                            ++this.dmg;
                            this.imageEventText.updateDialogOption(0, OPTIONS[4] + this.dmg + OPTIONS[1] + this.relicObtainChance + OPTIONS[2]);
                            this.imageEventText.updateDialogOption(1, OPTIONS[3]);
                        }

                        return;
                    case 1:
                        this.imageEventText.updateBodyText(ESCAPE_MSG);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.removeDialogOption(1);
                        this.screenNum = 1;
                        return;
                    default:
                        logger.info("ERROR: case " + buttonPressed + " should never be called");
                        return;
                }
            case 1:
                this.openMap();
        }

    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Scrap Ooze");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        FAIL_MSG = DESCRIPTIONS[1];
        SUCCESS_MSG = DESCRIPTIONS[2];
        ESCAPE_MSG = DESCRIPTIONS[3];
    }
}
