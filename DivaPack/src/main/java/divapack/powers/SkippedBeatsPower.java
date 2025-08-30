package divapack.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

public class SkippedBeatsPower extends BasePower {
    public static final String POWER_ID = ("DivaPack:" + SkippedBeatsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SkippedBeatsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int energy = EnergyPanel.getCurrentEnergy() + this.amount;
            ArrayList<P2PPlayer> players = SpireHelp.Multiplayer.Players.GetPlayers(true, true);
            while (energy != 0) {
                players.get(random.nextInt(players.size())).addPower(new DrawCardNextTurnPower(null, 1));
                energy --;
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (amount != 0)
            this.description += DESCRIPTIONS[1] + amount;
        this.description += DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SkippedBeatsPower(owner, this.amount);
    }
}
