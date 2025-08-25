package buxompack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;

import java.util.ArrayList;

public class AllyDrawTypeAction extends AbstractGameAction {
    P2PPlayer target;
    AbstractCard.CardType type;
    int amount;

    public AllyDrawTypeAction(P2PPlayer target, AbstractCard.CardType type, int amount) {
        this.target = target;
        this.type = type;
        this.amount = amount;
    }

    public AllyDrawTypeAction(P2PPlayer target, AbstractCard.CardType type) {
        this(target, type, 1);
    }

    public void update() {
        ArrayList<NetworkCard> cards = new ArrayList<>(target.drawPile);
        cards.removeIf((card) -> BuxomMod.BuxomMod.getType(card.ToStandard()) != type);
        while (amount != 0 && !cards.isEmpty()) {
            target.moveCard(cards.remove(0), CardGroup.CardGroupType.DRAW_PILE, CardGroup.CardGroupType.HAND);
            amount --;
        }

        this.isDone = true;
    }
}
