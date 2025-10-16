package buxompack.actions;

import BuxomMod.powers.CommonPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import spireTogether.network.P2P.P2PPlayer;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class TakeBuxomAction extends AbstractGameAction {
    P2PPlayer e;

    public TakeBuxomAction(P2PPlayer Player) {
        this.e = Player;
    }

    public void update() {
        if (e.hasPower(CommonPower.POWER_ID)) {
            for (int index = 0; index != e.powers.size(); index ++) {
                if (e.powers.get(0).realPowerID.equals(CommonPower.POWER_ID)) {
                    addToTop(new ApplyPowerAction(player, player, new CommonPower(player, player, e.powers.get(0).amount)));

                    break;
                }
            }
            e.removePower(CommonPower.POWER_ID);
        }

        this.isDone = true;
    }
}
