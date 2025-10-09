package hegemonpack.cards.skills;

import HegemonMod.cards.skills.Vector;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;
import spireTogether.util.SpireHelp;
import hegemonpack.cards.BaseCard;

public class Plague extends BaseCard {
    public static final String ID = ("HegemonPack:" + Plague.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public Plague() {
        super(ID, info); // calls the parent constructor

        cardsToPreview = new Vector();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addCard(NetworkCard.Generate(cardsToPreview), CardGroup.CardGroupType.HAND);
        addToBot(new ShowCardAction(cardsToPreview));
    }

    @Override public void upgrade() {
        super.upgrade();

        cardsToPreview.upgrade();
    }

    @Override public AbstractCard makeCopy() { return new Plague(); }
}
