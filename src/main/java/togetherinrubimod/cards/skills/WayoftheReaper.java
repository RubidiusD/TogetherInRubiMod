package togetherinrubimod.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.cards.skills.Punish;
import rubimod.character.Hegemon;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;
import spireTogether.util.SpireHelp;
import togetherinrubimod.cards.BaseCard;
import togetherinrubimod.util.CardStats;

public class WayoftheReaper extends BaseCard {
    public static final String ID = makeID(WayoftheReaper.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public WayoftheReaper() {
        super(ID, info); // calls the parent constructor

//        setExhaust(true);
        cardsToPreview = new Punish();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addCard(NetworkCard.Generate(cardsToPreview), CardGroup.CardGroupType.HAND);
    }

    @Override
    public void upgrade() {
        super.upgrade();

        cardsToPreview.upgrade();
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new WayoftheReaper();
    }
}
