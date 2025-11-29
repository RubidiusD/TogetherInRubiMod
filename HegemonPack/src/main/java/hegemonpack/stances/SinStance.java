package hegemonpack.stances;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class SinStance extends AbstractStance {
    public static final String STANCE_ID = ("HegemonPack:" + SinStance.class.getSimpleName());
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static long sfxId = -1;

    public SinStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.updateDescription();
    }

    @Override public void atStartOfTurn() {
        actionManager.addToTop(new ApplyPowerAction(player, player, new StrengthPower(player, 1)));
    }

    @Override public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0];
    }
}
