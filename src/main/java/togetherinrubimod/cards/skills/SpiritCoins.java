package togetherinrubimod.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import togetherinrubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import rubimod.powers.debuff.Bleeding;
import rubimod.powers.debuff.LeechToxin;
import togetherinrubimod.util.CardStats;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class SpiritCoins extends BaseCard {
    public static final String ID = makeID(SpiritCoins.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 8;
    private static final int MAGIC = 3;

    public SpiritCoins() {
        super(ID, info); // calls the parent constructor

        setBlock(BLOCK); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setExhaust(true);
        setSelfRetain(false, true);
        setCustomVar("Leech", 2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
        {
            for (int i = 0; i < magicNumber; i++) {
                e.addBlock(this.block);
            }
            e.addPower(new Bleeding(p, p));
            e.addPower(new LeechToxin(p, p, customVar("Leech")));
        }
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new SpiritCoins();
    }
}
