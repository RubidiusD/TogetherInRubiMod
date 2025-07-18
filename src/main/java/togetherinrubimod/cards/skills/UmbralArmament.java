package togetherinrubimod.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import togetherinrubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.buff.UmbralTempPower;
import togetherinrubimod.util.CardStats;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class UmbralArmament extends BaseCard {
    public static final String ID = makeID(UmbralArmament.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 2;

    public UmbralArmament() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new UmbralTempPower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new UmbralArmament();
    }
}
