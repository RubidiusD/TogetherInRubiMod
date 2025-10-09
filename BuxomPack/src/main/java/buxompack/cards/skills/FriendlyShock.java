package buxompack.cards.skills;

import BuxomMod.cards.ShockStatus;
import BuxomMod.characters.TheBuxom;
import buxompack.actions.ShockAction;
import buxompack.cards.BaseCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FriendlyShock extends BaseCard {
    public static final String ID = ("BuxomPack:" + FriendlyShock.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.NONE,
            0    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int SCALE = 5;

    public FriendlyShock() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("Scale", SCALE);
        setExhaust(true);
        cardsToPreview = new ShockStatus();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ShockAction(customVar("Scale"), magicNumber));
    }

    @Override public AbstractCard makeCopy() { return new FriendlyShock(); }
}
