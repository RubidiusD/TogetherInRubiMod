package hegemonpack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import spireTogether.monsters.CharacterEntity;

public class AllyBlockAction extends AbstractGameAction {
    public AllyBlockAction(AbstractCreature target, int amount) {
        this.amount = amount;
        this.target = target;
    }

    @Override public void update() {
        if (target instanceof CharacterEntity) {
            target.addBlock(amount);
        }

        this.isDone = true;
    }
}
