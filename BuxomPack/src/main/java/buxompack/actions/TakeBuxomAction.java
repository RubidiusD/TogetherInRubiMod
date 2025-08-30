package buxompack.actions;

import BuxomMod.powers.BouncePower;
import BuxomMod.powers.CommonPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import spireTogether.network.P2P.P2PPlayer;

public class TakeBuxomAction extends AbstractGameAction {
    P2PPlayer e;

    public TakeBuxomAction(P2PPlayer Player) {
        this.e = Player;
    }

    public void update() {
        if (e.hasPower(CommonPower.POWER_ID)) {
            for (int index = 0; index != e.powers.size(); index ++) {
                if (e.powers.get(0).realPowerID.equals(CommonPower.POWER_ID)) {
                    AbstractPlayer p = AbstractDungeon.player;
                    addToTop(new ApplyPowerAction(p, p, new CommonPower(p, p, e.powers.get(0).amount)));
                    e.removePower(CommonPower.POWER_ID);

                    break;
                }
            }
        }
        e.removePower(BouncePower.POWER_ID);

        this.isDone = true;
    }
}
