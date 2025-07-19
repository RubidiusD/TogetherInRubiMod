package togetherinrubimod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import rubimod.character.Hegemon;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;
import togetherinrubimod.cards.BaseCard;
import togetherinrubimod.util.CardStats;

public class BasicTolerance extends BaseCard {
    public static final String ID = makeID(BasicTolerance.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public BasicTolerance() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new ArtifactPower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new BasicTolerance();
    }
}
