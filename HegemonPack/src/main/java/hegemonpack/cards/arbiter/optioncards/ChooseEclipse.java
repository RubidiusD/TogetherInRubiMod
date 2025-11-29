package hegemonpack.cards.arbiter.optioncards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.stances.EclipseStance;

public class ChooseEclipse extends BaseCard {
    public static final String ID = ("HegemonPack:" + ChooseEclipse.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    public ChooseEclipse() {
        super(ID, info);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public void onChoseThisOption() {
        this.addToBot(new ChangeStanceAction(EclipseStance.STANCE_ID));
    }

    public AbstractCard makeCopy() { return new ChooseEclipse(); }
}
