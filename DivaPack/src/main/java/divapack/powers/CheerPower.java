package divapack.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dumbjokedivamod.cards.temporary.Lyric;
import dumbjokedivamod.powers.RhythmPower;

public class CheerPower extends BasePower implements OnReceivePowerPower {
    public static final String POWER_ID = ("DivaPack:" + CheerPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CheerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.amount2 = amount;
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2 += stackAmount;
        updateDescription();
    }

    @Override public void onSpecificTrigger() {
        if (amount2 != 0) {
            addToTop(new MakeTempCardInHandAction(new Lyric()));
            amount2 --;
            updateDescription();
        }
    }

    @Override public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(RhythmPower.POWER_ID)) {
            onSpecificTrigger();
        }

        return true;
    }

    @Override public void atStartOfTurn() {
        this.amount2 = amount;
        updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1];
    }

    @Override public AbstractPower makeCopy() { return new CheerPower(owner, amount); }
}
