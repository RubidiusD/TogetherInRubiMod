package divapack.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import divapack.powers.LeekPower;
import dumbjokedivamod.character.Diva;

public class Leek extends BaseCard {
    public static final String ID = ("DivaPack:" + Leek.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public Leek() {
        super(ID, info); // calls the parent constructor

        setMagic(1);
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LeekPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Leek();
    }
}
