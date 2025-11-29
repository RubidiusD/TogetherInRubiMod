package hegemonpack.cards.arbiter.skills.uncommon;

import HegemonMod.powers.buff.EnrichedPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;
import spireTogether.monsters.CharacterEntity;

public class Enrich extends BaseCard {
    public static final String ID = ("HegemonPack:" + Enrich.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Enrich() {
        super(ID, info); // calls the parent

        setMagic(MAGIC, UPG_MAGIC);
        setAllyTargetingRule(AllyCardTargeting.ALLY_ONLY);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        if (m instanceof CharacterEntity) {
            m.addPower(new EnrichedPower(m, magicNumber));
        }
    }

    @Override public AbstractCard makeCopy() { return new Enrich(); }
}
