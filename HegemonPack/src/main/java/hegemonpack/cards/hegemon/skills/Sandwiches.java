package hegemonpack.cards.hegemon.skills;

import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class Sandwiches extends BaseCard {
    public static final String ID = ("HegemonPack:" + Sandwiches.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Sandwiches() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setExhaust(true);

        tags.add(CardTags.HEALING);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        p.heal(this.magicNumber);
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(false, true))
            e.heal(this.magicNumber);
    }

    @Override public AbstractCard makeCopy() { return new Sandwiches(); }
}
