package hegemonpack.cards.hegemon.skills;

import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.ToxicPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;
import hegemonpack.cards.BaseCard;

public class SilverLiquor extends BaseCard {
    public static final String ID = ("HegemonPack:" + SilverLiquor.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 3;

    public SilverLiquor() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setExhaust(true, false);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new ToxicPower(p, magicNumber));
    }

    @Override public AbstractCard makeCopy() { return new SilverLiquor(); }
}
