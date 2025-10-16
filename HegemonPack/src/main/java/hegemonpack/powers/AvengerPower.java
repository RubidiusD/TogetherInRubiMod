package hegemonpack.powers;

import HegemonMod.cards.attacks.Punish;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hegemonpack.MessageReceiver;

public class AvengerPower extends BasePower implements MessageReceiver.AllyArtifactLostSubscriber {
    public static final String POWER_ID = ("HegemonPack:" + AvengerPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AvengerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.updateDescription();
    }

    @Override public void receiveAllyArtifactLost() {
        addToTop(new MakeTempCardInHandAction(new Punish(), amount));
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (amount == 1)
            this.description += amount + DESCRIPTIONS[1];
        else
            this.description += amount + DESCRIPTIONS[2];
    }

    @Override public AbstractPower makeCopy() { return new AvengerPower(owner, amount); }
}
