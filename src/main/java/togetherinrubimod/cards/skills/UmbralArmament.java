package togetherinrubimod.cards.skills;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;
import togetherinrubimod.cards.BaseCard;
import rubimod.character.Hegemon;
import togetherinrubimod.util.CardStats;

public class UmbralArmament extends BaseCard {
    public static final String ID = ("togetherinrubimod:" + UmbralArmament.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 3;

    public UmbralArmament() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addPower(new RegenPower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new UmbralArmament();
    }

    @SpirePatch2(clz= UmbralArmament.class, method= "use", paramtypez = {AbstractPlayer.class, AbstractMonster.class}, requiredModId = "rubimod")
    public static class UmbralArmamentPatch {
        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("RegenPower")) {
                        m.replace("$_ = rubimod.powers.buff.UmbralVenom($$)");
                    }
                }
            };
        }
    }
}
