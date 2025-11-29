package hegemonpack.cards.arbiter.attacks;

import HegemonMod.powers.debuff.Necrotoxin;
import HegemonMod.powers.debuff.Sin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;

public class Seethe extends BaseCard {
    public static final String ID = ("HegemonPack:" + Seethe.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 1;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Seethe() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new Necrotoxin(m, p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new Seethe(); }
}
