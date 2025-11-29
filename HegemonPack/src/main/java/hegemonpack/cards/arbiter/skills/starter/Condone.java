package hegemonpack.cards.arbiter.skills.starter;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.arbiter.optioncards.ChooseVirtue;
import hegemonpack.characters.Arbiter;
import hegemonpack.actions.AllyBlockAction;
import hegemonpack.stances.VirtueStance;
import hegemonpack.cards.BaseCard;

public class Condone extends BaseCard {
    public static final String ID = ("HegemonPack:" + Condone.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 8;

    public Condone() {
        super(ID, info); // calls the parent

        setBlock(BLOCK);
        setAllyTargetingRule(AllyCardTargeting.ALLY_ONLY);
        setCostUpgrade(1);

        cardsToPreview = new ChooseVirtue();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AllyBlockAction(m, block));
        addToBot(new ChangeStanceAction(new VirtueStance()));
    }

    @Override public AbstractCard makeCopy() { return new Condone(); }
}
