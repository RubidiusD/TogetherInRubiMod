package togetherinrubimod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BrutalityPower;
import rubimod.actions.ApplyNecrotoxinAction;
import rubimod.character.Hegemon;
import rubimod.powers.buff.PhoenixPower;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;
import togetherinrubimod.cards.BaseCard;
import togetherinrubimod.util.CardStats;

public class KindofHisThing extends BaseCard {
    public static final String ID = ("togetherinrubimod:" + KindofHisThing.class.getSimpleName());
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
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int artifact = p.getPower(ArtifactPower.POWER_ID).amount;
        addToBot(new RemoveSpecificPowerAction(p, p, ArtifactPower.POWER_ID));
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
        {
            e.addPower(new PhoenixPower(p, 1));
            if (magicNumber > 0)
                e.addPower(new BrutalityPower(p, magicNumber));
        }
        addToBot(new ApplyPowerAction(p, p, new PhoenixPower(p, 1)));
        addToBot(new ApplyNecrotoxinAction(p, p, 15));
        if (magicNumber > 0)
            addToBot(new ApplyPowerAction(p, p, new BrutalityPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, artifact)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new KindofHisThing();
    }
}
