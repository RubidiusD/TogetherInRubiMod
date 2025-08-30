package buxompack.cards.skills;

import BuxomMod.characters.TheBuxom;
import buxompack.actions.AllyDrawTypeAction;
import buxompack.cards.BaseCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class GainBearings extends BaseCard {
    public static final String ID = ("BuxomPack:" + GainBearings.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public GainBearings() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
            addToBot(new AllyDrawTypeAction(e, CardType.STATUS, magicNumber));
        }
        addToBot(new DrawCardAction(1));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new GainBearings();
    }
}
