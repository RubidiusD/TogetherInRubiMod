package divapack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dumbjokedivamod.cards.starting.Defend;

import java.util.ArrayList;
import java.util.UUID;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class ImitateAction extends AbstractGameAction {
    UUID uuid;

    public ImitateAction(int amount, UUID uuid) {
        this.uuid = uuid;
        this.amount = amount;
        this.actionType = ActionType.SPECIAL;
    }

    @Override public void update() {
        ArrayList<AbstractCard> cards = new ArrayList<>(player.masterDeck.group);
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).uuid == uuid) {
                player.masterDeck.removeCard(cards.get(i));
            }
            if (cards.get(i).block == -1) {
                cards.remove(i);
                i -= 1;
            }
        }

        AbstractCard card;
        if (cards.isEmpty()) {
            card = new Defend();
            addToTop(new AddCardToDeckAction(card));
        } else {
            card = cards.get(random.nextInt(cards.size()));
        }
        card.baseBlock += amount;
        card.upgradedBlock = true;

        isDone = true;
    }
}
