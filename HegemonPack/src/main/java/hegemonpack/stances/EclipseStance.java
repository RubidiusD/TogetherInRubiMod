package hegemonpack.stances;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import hegemonpack.powers.JudgementPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class EclipseStance extends AbstractStance {
    public static final String STANCE_ID = ("HegemonPack:" + EclipseStance.class.getSimpleName());
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static long sfxId = -1;

    public EclipseStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.updateDescription();
    }

    @Override public void onEnterStance() {
        if (player.hasPower(DexterityPower.POWER_ID)) {
            actionManager.addToTop(new RemoveSpecificPowerAction(player, player, DexterityPower.POWER_ID));
            actionManager.addToTop(new ApplyPowerAction(player, player, new JudgementPower(player, player.getPower(DexterityPower.POWER_ID).amount)));
        }
    }

    @Override public void onPlayCard(AbstractCard card) {
        if (card.type == AbstractCard.CardType.SKILL) {
            actionManager.addToBottom(new DrawCardAction(1));
        }
    }

    @Override public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0] + (player.hasPower(DexterityPower.POWER_ID) ? player.getPower(DexterityPower.POWER_ID).amount : 0) + stanceString.DESCRIPTION[1];
    }
}
