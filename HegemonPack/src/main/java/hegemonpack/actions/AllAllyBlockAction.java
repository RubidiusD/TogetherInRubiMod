package hegemonpack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class AllAllyBlockAction extends AbstractGameAction {
    public AllAllyBlockAction(int amount) {
        this.amount = amount;
    }

    @Override public void update() {
        for (P2PPlayer p : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
            p.addBlock(amount);
        }

        this.isDone = true;
    }
}
