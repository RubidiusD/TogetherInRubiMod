package hegemonpack.relics.arbiter;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import hegemonpack.characters.Arbiter;
import hegemonpack.relics.BaseRelic;
import hegemonpack.stances.EclipseStance;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class StarterRelic extends BaseRelic {
    private static final String NAME = StarterRelic.class.getSimpleName();
    public static final String ID = ("HegemonPack:" + NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public StarterRelic() {
        super(ID, NAME, Arbiter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override public void onEquip() {
        super.onEquip();
    }

    @Override
    public void atTurnStartPostDraw() {
        int sum = 0;
        for (AbstractCard card : player.hand.group) {
            if (Math.min(card.cost, card.costForTurn) > 0) {
                sum += Math.min(card.cost, card.costForTurn);
            }
        }

        if (sum < EnergyPanel.getCurrentEnergy()) {
            addToTop(new ChangeStanceAction(EclipseStance.STANCE_ID));
        }
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
