package hegemonpack.cards.powers;

import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.PhoenixPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BrutalityPower;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;
import hegemonpack.cards.BaseCard;
import hegemonpack.cards.KindofYourThing;

public class KindofHisThing extends BaseCard {
    public static final String ID = ("HegemonPack:" + KindofHisThing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            0    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 2;

    public KindofHisThing() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setCustomVar("Phoenix", 2);

        cardsToPreview = new KindofYourThing();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
        {
            e.addPower(new PhoenixPower(p, customVar("Phoenix")));
            if (magicNumber > 0)
                e.addPower(new BrutalityPower(p, magicNumber));
        }
        addToBot(new ApplyPowerAction(p, p, new PhoenixPower(p, customVar("Phoenix"))));
        if (magicNumber > 0)
            addToBot(new ApplyPowerAction(p, p, new BrutalityPower(p, magicNumber)));
        addToBot(new MakeTempCardInHandAction(cardsToPreview.makeCopy()));
    }

    @Override public AbstractCard makeCopy() { return new KindofHisThing(); }
}
