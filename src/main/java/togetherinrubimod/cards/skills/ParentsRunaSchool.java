package togetherinrubimod.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Study;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.character.Hegemon;
import togetherinrubimod.actions.RunSchoolAction;
import togetherinrubimod.cards.BaseCard;
import togetherinrubimod.util.CardStats;

public class ParentsRunaSchool extends BaseCard {
    public static final String ID = makeID(ParentsRunaSchool.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public ParentsRunaSchool() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        cardsToPreview = new Study();
    }

    @Override
    public void upgrade() {
        super.upgrade();

        cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RunSchoolAction(magicNumber > 0));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new ParentsRunaSchool();
    }
}
