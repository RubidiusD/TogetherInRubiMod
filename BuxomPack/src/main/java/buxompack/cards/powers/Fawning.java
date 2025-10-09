package buxompack.cards.powers;

import BuxomMod.characters.TheBuxom;
import buxompack.actions.ShockAction;
import buxompack.cards.BaseCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fawning extends BaseCard {
    public static final String ID = ("BuxomPack:" + Fawning.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Fawning() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ShockAction(customVar("Scale"), magicNumber));
    }

    @Override public AbstractCard makeCopy() { return new Fawning(); }
}
