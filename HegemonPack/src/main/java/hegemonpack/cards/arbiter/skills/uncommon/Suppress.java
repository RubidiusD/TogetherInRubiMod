package hegemonpack.cards.arbiter.skills.uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.cards.arbiter.attacks.Seethe;
import hegemonpack.characters.Arbiter;

public class Suppress extends BaseCard {
    public static final String ID = ("HegemonPack:" + Suppress.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Suppress() {
        super(ID, info); // calls the parent

        setMagic(MAGIC, UPG_MAGIC);

        cardsToPreview = new Seethe();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInDrawPileAction(cardsToPreview, 1, true, true));
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override public AbstractCard makeCopy() { return new Suppress(); }
}
