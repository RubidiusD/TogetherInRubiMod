package divapack.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import divapack.powers.CheerPower;
import dumbjokedivamod.character.Diva;

public class CrowdCheer extends BaseCard {
    public static final String ID = ("DivaPack:" + CrowdCheer.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public CrowdCheer() {
        super(ID, info); // calls the parent constructor

        setInnate(false, true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CheerPower(p, 1)));
    }

    @Override public AbstractCard makeCopy() { return new CrowdCheer(); }
}
