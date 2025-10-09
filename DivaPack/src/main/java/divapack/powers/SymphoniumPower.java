package divapack.powers;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dumbjokedivamod.cards.common.SymphonicGuard;
import dumbjokedivamod.cards.common.SymphonicStrike;
import dumbjokedivamod.cards.uncommon.SymphonicCharm;
import dumbjokedivamod.cards.uncommon.SymphonicResonance;
import dumbjokedivamod.cards.uncommon.SymphonicWrath;
import dumbjokedivamod.powers.SymphonyPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.entities.NetworkPower;
import spireTogether.util.SpireHelp;

public class SymphoniumPower extends BasePower {
    public static final String POWER_ID = ("DivaPack:" + SymphoniumPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SymphoniumPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(-1);
        amount = -1;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override public AbstractPower makeCopy() {
        return new SymphoniumPower(owner);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ implementation

    @SpirePatches2({
            @SpirePatch2(clz= SymphonicStrike.class,    method= "applyPowers", paramtypez = {}),
            @SpirePatch2(clz= SymphonicGuard.class,     method= "applyPowers", paramtypez = {}),
            @SpirePatch2(clz= SymphonicCharm.class,     method= "applyPowers", paramtypez = {}),
            @SpirePatch2(clz= SymphonicResonance.class, method= "applyPowers", paramtypez = {}),
            @SpirePatch2(clz= SymphonicWrath.class,     method= "applyPowers", paramtypez = {})
    })
    public static class SymphoniumPatch {
        @SpireInstrumentPatch public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getMethodName().equals("hasPower")) {m.replace("$_ = ($proceed($$) || player.hasPower(divapack.powers.SymphoniumPower.POWER_ID));");}
                    else if (m.getMethodName().equals("getPower")) {
                        m.replace("if (player.hasPower(divapack.powers.SymphoniumPower.POWER_ID)) {" +
                                "   $_ = player.getPower(divapack.powers.SymphoniumPower.POWER_ID);" +
                                "} else {" +
                                "   $_ = player.getPower(dumbjokedivamod.powers.SymphonyPower.POWER_ID);}");
                    }
                }

                @Override public void edit(FieldAccess f) throws CannotCompileException {
                    if (f.getFieldName().equals("amount")) {
                        f.replace("$_ = divapack.powers.SymphoniumPower.getSymphonic(player);");
                    }
                }
            };
        }
    }

    public static int getSymphonic(AbstractCreature guy) {
        int total = 0;
        if (guy.hasPower(SymphoniumPower.POWER_ID)) {
            for (P2PPlayer p : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
                if (p.hasPower(SymphonyPower.POWER_ID)) {
                    for (NetworkPower po : p.powers) {
                        if (po.realPowerID.equals(SymphonyPower.POWER_ID)) {
                            total += po.amount;
                            break;
                        }
                    }
                }
            }
        }
        if (guy.hasPower(SymphonyPower.POWER_ID)) {
            total += guy.getPower(SymphonyPower.POWER_ID).amount;
        }
        return total;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ fixing other symphonic cards
    @SpireEnum public static AbstractCard.CardTags Symphonic;

    @SpirePatches2({
        @SpirePatch2(clz= SymphonicStrike.class,    method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
        @SpirePatch2(clz= SymphonicGuard.class,     method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
        @SpirePatch2(clz= SymphonicCharm.class,     method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
        @SpirePatch2(clz= SymphonicResonance.class, method= SpirePatch.CONSTRUCTOR, paramtypez = {}),
        @SpirePatch2(clz= SymphonicWrath.class,     method= SpirePatch.CONSTRUCTOR, paramtypez = {})
    })
    public static class TagPatch {
        @SpirePostfixPatch public static void Postfix(AbstractCard __instance) {
            __instance.tags.add(Symphonic);
        }
    }

    @SpirePatch2(clz= SymphonyPower.class, method= "checkCard")
    public static class TagPatch2 {
        public static void Replace(SymphonyPower __instance, AbstractCard c) {
            if (c.hasTag(Symphonic)) {c.applyPowers();}
        }
    }
}
