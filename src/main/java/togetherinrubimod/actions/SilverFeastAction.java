package togetherinrubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class SilverFeastAction extends AbstractGameAction {
    public SilverFeastAction() { super(); }

    @Override
    public void update()
    {
        for (AbstractMonster enemy : AbstractDungeon.getMonsters().monsters) {
            addToTop(new RemoveDebuffsAction(enemy));
        }

        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
            e.draw(1);
            e.gainEnergy(1);
        }
    }
}
