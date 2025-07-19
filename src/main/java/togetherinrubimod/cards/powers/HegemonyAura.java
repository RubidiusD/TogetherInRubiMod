package togetherinrubimod.cards.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.character.Hegemon;
import rubimod.powers.buff.HegemonyPower;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;
import togetherinrubimod.cards.BaseCard;
import togetherinrubimod.util.CardStats;

public class HegemonyAura extends BaseCard {
    public static final String ID = makeID(HegemonyAura.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    public HegemonyAura() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new HegemonyPower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new HegemonyAura();
    }
}
