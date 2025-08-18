package togetherinrubimod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import rubimod.cards.skills.Punish;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.entities.NetworkPower;

import java.util.ArrayList;

public class AvengerPower extends BasePower {
    public static final String POWER_ID = ("togetherinrubimod:" + AvengerPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AvengerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.updateDescription();
    }

    @Override
    public void onAllyChangedPowers(P2PPlayer player, ArrayList<NetworkPower> oldPowers, ArrayList<NetworkPower> newPowers, ArrayList<NetworkPower> gainedPowers, ArrayList<NetworkPower> lostPowers) {
        for (NetworkPower p : lostPowers)
            if (p.realPowerID.equals(ArtifactPower.POWER_ID))
            {
                addToTop(new MakeTempCardInHandAction(new Punish(), amount));
                return;
            }
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
        return new AvengerPower(owner, amount);
    }
}
