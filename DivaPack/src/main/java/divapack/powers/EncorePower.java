package divapack.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dumbjokedivamod.powers.RhythmPower;

public class EncorePower extends BasePower {
    public static final String POWER_ID = ("DivaPack:" + EncorePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EncorePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(-1);
        this.amount = -1;
    }

    @Override
    public void atStartOfTurn() {
        addToTop(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        if (owner.hasPower(RhythmPower.POWER_ID)) {
            owner.getPower(RhythmPower.POWER_ID).reducePower(owner.getPower(RhythmPower.POWER_ID).amount);
        } else {
            addToTop(new ApplyPowerAction(owner, owner, new RhythmPower(owner, 0)));
        }
        this.flash();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (amount == 1)
            this.description += amount + DESCRIPTIONS[1];
        else
            this.description += amount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new EncorePower(owner);
    }
}
