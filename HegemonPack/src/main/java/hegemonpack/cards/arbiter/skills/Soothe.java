package hegemonpack.cards.arbiter.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.actions.AllyBlockAction;
import hegemonpack.cards.BaseCard;
import hegemonpack.powers.VirtuePower;
import spireTogether.monsters.CharacterEntity;

public class Soothe extends BaseCard {
    public static final String ID = ("HegemonPack:" + Soothe.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 1;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 2;

    public Soothe() {
        super(ID, info); // calls the parent

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);

        setAllyTargetingRule(AllyCardTargeting.ALLY_ONLY);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        if (m instanceof CharacterEntity) {
            m.addPower(new VirtuePower(m, magicNumber));
        }
        addToBot(new AllyBlockAction(m, block));
    }

    @Override public AbstractCard makeCopy() { return new Soothe(); }
}
