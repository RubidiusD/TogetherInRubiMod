package divapack.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dumbjokedivamod.powers.RhythmPower;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class LeekPower extends BasePower implements OnReceivePowerPower {
    public static final String POWER_ID = ("DivaPack:" + LeekPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public LeekPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(RhythmPower.POWER_ID)) {
            this.flash();
            for (P2PPlayer pe : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
                pe.heal(amount);
            }
        }

        return true;
    }

    @Override public void atStartOfTurn() {
        addToTop(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override public AbstractPower makeCopy() {
        return new LeekPower(owner, amount);
    }
}
