package divapack.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dumbjokedivamod.Actions.ApplyRhythmAction;
import dumbjokedivamod.powers.RhythmPower;

public class ReprisePower extends BasePower {
    public static final String POWER_ID = ("DivaPack:" + ReprisePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ReprisePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(-1);
        this.amount = -1;
    }

    @Override public void atStartOfTurn() {
        addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        addToTop(new ApplyRhythmAction());
        if (owner.hasPower(RhythmPower.POWER_ID)) {
            addToTop(new RemoveSpecificPowerAction(owner, owner, RhythmPower.POWER_ID));
        }
        this.flash();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override public AbstractPower makeCopy() { return new ReprisePower(owner);
    }


}
