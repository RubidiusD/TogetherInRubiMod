package hegemonpack.cards.arbiter.skills.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.actions.RandomAllyBlockAction;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;

public class Shroud extends BaseCard {
    public static final String ID = ("HegemonPack:" + Shroud.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 5;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public Shroud() {
        super(ID, info); // calls the parent

        setBlock(BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
        setSelfRetain(true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (int index = 0; index != magicNumber; index ++) {
            addToBot(new RandomAllyBlockAction(block));
        }
    }

    @Override public AbstractCard makeCopy() { return new Shroud(); }
}
