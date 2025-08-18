package togetherinrubimod.cards.skills;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import rubimod.cards.skills.Punish;
import rubimod.character.Hegemon;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;
import spireTogether.util.SpireHelp;
import togetherinrubimod.cards.BaseCard;
import togetherinrubimod.util.CardStats;

public class Plague extends BaseCard {
    public static final String ID = ("togetherinrubimod:" + Plague.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    public Plague() {
        super(ID, info); // calls the parent constructor

        cardsToPreview = new Punish();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addCard(NetworkCard.Generate(cardsToPreview), CardGroup.CardGroupType.HAND);
    }

    @Override
    public void upgrade() {
        super.upgrade();

        cardsToPreview.upgrade();
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Plague();
    }

    @SpirePatch2(clz= Plague.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}, requiredModId = "rubimod")
    public static class VectorPatch {
        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("Punish")) {
                        m.replace("$_ = rubimod.cards.skills.Vector()");
                    }
                }
            };
        }
    }
}
