package divapack.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.actions.ImitateAction;
import divapack.cards.BaseCard;

public class Imitate extends BaseCard {
    public static final String ID = ("DivaPack:" + Imitate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0    // card cost!! (-1 is X, -2 is unplayable)
    );

    public static final int MAGIC = 3;
    public static final int UPG_MAGIC = 2;

    public Imitate() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ImitateAction(magicNumber, uuid));
    }

    @Override public void triggerWhenDrawn() {
        addToTop(new DrawCardAction(1));
    }

    @Override public void upgrade() {
        super.upgrade();
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    @Override public boolean canUpgrade() {
        return true;
    }

    @Override public AbstractCard makeCopy() { return new Imitate(); }
}
