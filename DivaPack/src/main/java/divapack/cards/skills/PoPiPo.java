package divapack.cards.skills;

import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import divapack.cards.attacks.Po;
import dumbjokedivamod.character.Diva;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;
import spireTogether.util.SpireHelp;

public class PoPiPo extends BaseCard {
    public static final String ID = ("DivaPack:" + PoPiPo.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public PoPiPo() {
        super(ID, info); // calls the parent constructor

        this.cardsToPreview = new Po();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addCard(NetworkCard.Generate(cardsToPreview), CardGroup.CardGroupType.HAND);
        addToBot(new ShowCardAction(cardsToPreview));
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.cardsToPreview.upgrade();
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new PoPiPo();
    }
}
