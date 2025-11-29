package hegemonpack.stances;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import hegemonpack.actions.AllAllyBlockAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class VirtueStance extends AbstractStance {
    public static final String STANCE_ID = ("HegemonPack:" + VirtueStance.class.getSimpleName());
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static long sfxId = -1;

    public VirtueStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.updateDescription();
    }

    @Override public void atStartOfTurn() {
        actionManager.addToTop(new ApplyPowerAction(player, player, new DexterityPower(player, 1)));
    }

    @Override public void onExitStance() {
        if (player.hasPower(DexterityPower.POWER_ID)) {
            actionManager.addToTop(new AllAllyBlockAction(player.getPower(DexterityPower.POWER_ID).amount));
        }
    }

    @Override public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0] + (player.hasPower(DexterityPower.POWER_ID) ? player.getPower(DexterityPower.POWER_ID).amount : 0) + stanceString.DESCRIPTION[1];
    }
}
