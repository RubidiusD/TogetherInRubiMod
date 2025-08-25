package divapack.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import divapack.powers.EncorePower;
import divapack.util.CardStats;
import dumbjokedivamod.character.Diva;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class GrandEncore extends BaseCard {
    public static final String ID = ("DivaPack:" + GrandEncore.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.NONE,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    public GrandEncore() {
        super(ID, info); // calls the parent constructor

        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new EncorePower(p));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new GrandEncore();
    }
}
