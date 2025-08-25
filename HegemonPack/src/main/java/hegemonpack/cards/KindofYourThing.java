package hegemonpack.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.util.CardStats;

public class KindofYourThing extends BaseCard {
    public static final String ID = ("HegemonPack:" + KindofYourThing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.SPECIAL,
            CardTarget.ALL,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 5;

    public KindofYourThing() {
        super(ID, info); // calls the parent

        setSelfRetain(true);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, magicNumber, DamageInfo.DamageType.HP_LOSS)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new KindofYourThing();
    }
}
