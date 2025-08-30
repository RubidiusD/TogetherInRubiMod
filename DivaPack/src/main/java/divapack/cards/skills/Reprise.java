package divapack.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import divapack.powers.ReprisePower;
import dumbjokedivamod.character.Diva;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class Reprise extends BaseCard {
    public static final String ID = ("DivaPack:" + Reprise.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.NONE,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    public Reprise() {
        super(ID, info); // calls the parent constructor

        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new ReprisePower(p));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Reprise();
    }
}
