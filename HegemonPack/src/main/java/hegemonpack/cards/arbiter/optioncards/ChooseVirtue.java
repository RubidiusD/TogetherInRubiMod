package hegemonpack.cards.arbiter.optioncards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.stances.VirtueStance;

public class ChooseVirtue extends BaseCard {
    public static final String ID = ("HegemonPack:" + ChooseVirtue.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    public ChooseVirtue() {
        super(ID, info);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public void onChoseThisOption() {
        this.addToBot(new ChangeStanceAction(VirtueStance.STANCE_ID));
    }

    public AbstractCard makeCopy() { return new ChooseVirtue(); }
}
