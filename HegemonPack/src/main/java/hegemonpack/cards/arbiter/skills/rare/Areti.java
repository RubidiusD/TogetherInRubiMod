package hegemonpack.cards.arbiter.skills.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;
import hegemonpack.powers.VirtuePower;
import spireTogether.monsters.CharacterEntity;

public class Areti extends BaseCard {
    public static final String ID = ("HegemonPack:" + Areti.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 5;

    public Areti() {
        super(ID, info); // calls the parent

        setMagic(MAGIC);
        setCostUpgrade(1);
        setAllyTargetingRule(AllyCardTargeting.ALLY_ONLY);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        if (m instanceof CharacterEntity) {
            m.addPower(new VirtuePower(m, magicNumber));
        }
    }

    @Override public AbstractCard makeCopy() { return new Areti(); }
}
