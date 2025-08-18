package togetherinrubimod.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.character.Hegemon;
import togetherinrubimod.actions.RunSchoolAction;
import togetherinrubimod.cards.BaseCard;
import togetherinrubimod.util.CardStats;

public class ParentsRunaSchool extends BaseCard {
    public static final String ID = ("togetherinrubimod:" + ParentsRunaSchool.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public ParentsRunaSchool() {
        super(ID, info); // calls the parent constructor

        setCostUpgrade(0);
        cardsToPreview = new Insight();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RunSchoolAction());
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new ParentsRunaSchool();
    }
}
