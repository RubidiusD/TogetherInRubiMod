package togetherinrubimod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import togetherinrubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Sin;
import togetherinrubimod.util.CardStats;
import togetherinrubimod.powers.Sinner;

public class UtterContempt extends BaseCard {
    public static final String ID = ("togetherinrubimod:" + UtterContempt.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;

    public UtterContempt() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setExhaust(true);
        setCostUpgrade(1);
        setInnate(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new Sin(m, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new Sinner(m)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new UtterContempt();
    }
}
