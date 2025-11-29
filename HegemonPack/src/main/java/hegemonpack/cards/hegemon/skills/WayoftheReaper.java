package hegemonpack.cards.hegemon.skills;

import HegemonMod.cards.attacks.Punish;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;
import spireTogether.util.SpireHelp;
import hegemonpack.cards.BaseCard;

public class WayoftheReaper extends BaseCard {
    public static final String ID = ("HegemonPack:" + WayoftheReaper.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public WayoftheReaper() {
        super(ID, info); // calls the parent constructor

        setMagic(0, 2);

        cardsToPreview = new Punish();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addCard(NetworkCard.Generate(cardsToPreview), CardGroup.CardGroupType.HAND);
        if (magicNumber != 0) {
            addToBot(new MakeTempCardInHandAction(cardsToPreview, 2));
        } else {
            addToBot(new ShowCardAction(cardsToPreview));
        }
    }

    @Override public void upgrade() {
        super.upgrade();

        cardsToPreview.upgrade();
    }

    @Override public AbstractCard makeCopy() { return new WayoftheReaper(); }
}
