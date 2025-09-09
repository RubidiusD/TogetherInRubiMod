package divapack.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import divapack.powers.SymphoniumPower;
import dumbjokedivamod.character.Diva;

public class Symphonium extends BaseCard {
    public static final String ID = ("DivaPack:" + Symphonium.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public Symphonium() {
        super(ID, info); // calls the parent constructor

        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SymphoniumPower(p)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Symphonium();
    }
}
