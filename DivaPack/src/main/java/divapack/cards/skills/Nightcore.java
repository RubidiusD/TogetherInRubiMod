package divapack.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import dumbjokedivamod.character.Diva;
import dumbjokedivamod.powers.CaptivationPower;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class Nightcore extends BaseCard {
    public static final String ID = ("DivaPack:" + Nightcore.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 20;
    private static final int UPG_MAGIC = 15;
    private static final int TAX = 5;

    public Nightcore() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("Tax", TAX);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new CaptivationPower(null, customVar("Tax")));
        addToBot(new ApplyPowerAction(p, p, new CaptivationPower(p, customVar("Tax"))));
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber, (AbstractMonster monster) -> new CaptivationPower(monster, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new Nightcore(); }
}
