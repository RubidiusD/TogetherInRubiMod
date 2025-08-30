package divapack.cards.skills;

import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import divapack.cards.BaseCard;
import dumbjokedivamod.character.Diva;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class Homophony extends BaseCard {
    public static final String ID = ("DivaPack:" + Homophony.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public static final int MAGIC = 1;
    public static final int UPG_MAGIC = 1;

    public Homophony() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new DuplicationPower(p, magicNumber));
        this.addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty();
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Homophony();
    }
}
