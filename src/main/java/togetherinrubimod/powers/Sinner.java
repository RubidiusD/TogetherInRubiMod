package togetherinrubimod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static togetherinrubimod.TogetherinRubiMod.makeID;

public class Sinner extends BasePower {
    public static final String POWER_ID = makeID(Sinner.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public Sinner(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(-1);
        amount = -1;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
