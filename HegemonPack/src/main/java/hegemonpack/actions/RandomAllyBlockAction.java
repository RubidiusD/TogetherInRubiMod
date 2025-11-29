package hegemonpack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import spireTogether.util.SpireHelp;

public class RandomAllyBlockAction extends AbstractGameAction {
    public RandomAllyBlockAction(int amount) {
        this.amount = amount;
    }

    @Override public void update() {
        if (!SpireHelp.Multiplayer.Players.IsAloneInRoom(true, null)) {
            SpireHelp.Multiplayer.Players.GetRandomPlayer(true, true).addBlock(amount);
        }

        this.isDone = true;
    }
}
