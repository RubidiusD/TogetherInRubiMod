package hegemonpack.cards.hegemon.skills;

import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.actions.RunSchoolAction;
import hegemonpack.cards.BaseCard;

public class ParentsRunaSchool extends BaseCard {
    public static final String ID = ("HegemonPack:" + ParentsRunaSchool.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public ParentsRunaSchool() {
        super(ID, info); // calls the parent constructor

        setCostUpgrade(0);
        cardsToPreview = new Insight();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RunSchoolAction());
    }

    @Override public AbstractCard makeCopy() { return new ParentsRunaSchool(); }
}
