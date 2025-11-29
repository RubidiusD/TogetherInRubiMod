package hegemonpack.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import spireTogether.network.P2P.P2PPlayer;

public class VirtuePower extends BasePower {
    public static final String POWER_ID = ("HegemonPack:" + VirtuePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public VirtuePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractPower highest = null;
        for (AbstractPower p : owner.powers) {
            if (p.type == PowerType.BUFF && !p.ID.equals(POWER_ID) && (p.canGoNegative || p.amount != -1)) {
                if (highest == null || p.amount > highest.amount) {
                    highest = p;
                }
            }
        }

        if (highest != null) {
            addToTop(new ReducePowerAction(owner, owner, this, (amount + 1) / 2));
            highest.stackPower((amount + 1) / 2);
        }
    }

    @Override
    public void onFFGainedBlock(P2PPlayer source, Integer gainAmount) {
        addToTop(new GainBlockAction(owner, amount));
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override public AbstractPower makeCopy() { return new VirtuePower(owner, amount); }
}
