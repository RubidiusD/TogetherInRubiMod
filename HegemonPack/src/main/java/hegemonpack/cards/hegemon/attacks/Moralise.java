package hegemonpack.cards.hegemon.attacks;

import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.Ginger;
import hegemonpack.actions.MoraliseAction;
import hegemonpack.cards.BaseCard;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class Moralise extends BaseCard {
    public static final String ID = ("HegemonPack:" + Moralise.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 0;

    public Moralise() {
        super(ID, info); // calls the parent

        setCostUpgrade(0);
        setDamage(DAMAGE);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MoraliseAction(p));
    }

    @Override public void triggerOnGlowCheck() {
        this.glowColor = shouldGlow() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public static boolean shouldGlow() {
        for (P2PPlayer p : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
            if (!p.hasPower(ArtifactPower.POWER_ID) && !p.hasRelic(Ginger.ID))
                return false;
        }

        return true;
    }

    @Override public AbstractCard makeCopy() { return new Moralise(); }
}
