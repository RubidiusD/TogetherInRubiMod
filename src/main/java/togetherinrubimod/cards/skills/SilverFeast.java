package togetherinrubimod.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import togetherinrubimod.actions.SilverFeastAction;
import togetherinrubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import togetherinrubimod.util.CardStats;

public class SilverFeast extends BaseCard {
    public static final String ID = makeID(SilverFeast.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    public SilverFeast() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SilverFeastAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SilverFeast();
    }
}
