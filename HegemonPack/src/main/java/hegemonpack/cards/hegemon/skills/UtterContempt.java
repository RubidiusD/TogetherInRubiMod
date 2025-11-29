package hegemonpack.cards.hegemon.skills;

import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.Sin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.powers.Sinner;

public class UtterContempt extends BaseCard {
    public static final String ID = ("HegemonPack:" + UtterContempt.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;

    public UtterContempt() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setExhaust(true);
        setInnate(false, true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new Sinner(m)));
    }

    @Override public AbstractCard makeCopy() { return new UtterContempt(); }
}
