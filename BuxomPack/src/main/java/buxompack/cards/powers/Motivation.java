package buxompack.cards.powers;

import BuxomMod.characters.TheBuxom;
import BuxomMod.patches.CustomTags;
import buxompack.cards.BaseCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class Motivation extends BaseCard {
    public static final String ID = ("BuxomPack:" + Motivation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.NONE,
            -2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 2;

    public Motivation() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);

        tags.add(CustomTags.BOUNCY);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.type = CardType.STATUS;
        boolean result = super.canUse(p, m);
        this.type = info.cardType;
        return result;
    }

    @Override public void triggerOnExhaust() {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
            e.addPower(new DrawPower(null, 1));
            if (magicNumber != 0) {
                e.draw(magicNumber);
            }
        }
    }

    @Override public AbstractCard makeCopy() { return new Motivation(); }
}
