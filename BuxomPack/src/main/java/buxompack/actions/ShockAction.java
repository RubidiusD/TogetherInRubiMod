package buxompack.actions;

import BuxomMod.cards.ShockStatus;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;
import spireTogether.util.SpireHelp;

import static BuxomMod.BuxomMod.braManager;

public class ShockAction extends AbstractGameAction {
    private int scale = 0;
    private int amount = 0;

    public ShockAction(int scale, int amount) {
        this.scale = scale;
        this.amount = amount;
    }

    public void update() {
        AbstractCard shockStatus = new ShockStatus();
        shockStatus.baseDamage += this.scale * braManager.buxomCounterThisTurn;

        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            for (int i = 0; i != this.amount; i ++)
                e.addCard(NetworkCard.Generate(shockStatus), CardGroup.CardGroupType.DRAW_PILE);

        this.isDone = true;
    }
}
