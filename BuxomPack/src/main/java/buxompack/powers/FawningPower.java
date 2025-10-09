package buxompack.powers;

import BuxomMod.powers.MilkPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.entities.NetworkCardActionData;

import java.util.ArrayList;

public class FawningPower extends BasePower {
    public static final String POWER_ID = ("BuxomPack:" + FawningPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public FawningPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void onAllyChangedCards(P2PPlayer player, CardGroup.CardGroupType groupType, ArrayList<AbstractCard> gainedCards, ArrayList<AbstractCard> lostCards) {
        if (player.IsPlayerInSameRoomAndAction() && groupType == CardGroup.CardGroupType.EXHAUST_PILE && !gainedCards.isEmpty()) {
            addToTop(new ApplyPowerAction(owner, owner, new MilkPower(owner, owner, this.amount)));
            System.out.println("Milking through Method 2!! ----------------------------------");
        }
    }

    @Override public void onFFExhausted(P2PPlayer source, NetworkCardActionData data) {
        if (source.IsPlayerInSameRoom())
            System.out.println("Milking through Method 1!! 1111111111111111111111111111111111111111");
    }

    @Override public AbstractPower makeCopy() { return new FawningPower(owner, amount); }
}
