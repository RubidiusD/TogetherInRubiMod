package divapack;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import spireTogether.cards.CustomMultiplayerCard;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class JustClickPatches {
    public static boolean thing;

    @SpirePatch2(clz = AbstractPlayer.class, method= "clickAndDragCards", requiredModId= "spireTogether")
    public static class JustClickPatch {
        @SpireInsertPatch(rlocs = {28, 15}) public static void Insert(AbstractPlayer __instance) {
            if (thing && (InputHelper.justClickedLeft || CInputHelper.isJustPressed(0)) && __instance.hoveredCard != null && !AbstractDungeon.isScreenUp) {
                try {
                    AutoplayCardFor(__instance);
                } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    System.out.println("------- Unsuccessful Auto-playing of TiS Card --------------------");
                }
            }
        }

        public static void AutoplayCardFor(AbstractPlayer p) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            System.out.println("Cannae Autoplay that Card for ye lassie :/");
        }
    }

    @SpirePatch2(clz= JustClickPatch.class, method= "AutoplayCardFor", paramtypez= {AbstractPlayer.class}, requiredModId = "spireTogether")
    public static class AutoplayPatch {
        @SpireInstrumentPatch public static void Instrument(AbstractPlayer __instance) {
            if (thing && (InputHelper.justClickedLeft || CInputHelper.isJustPressed(0)) && __instance.hoveredCard != null && !AbstractDungeon.isScreenUp && __instance.hoveredCard instanceof CustomMultiplayerCard)
                try {
                    CustomMultiplayerCard clickedCard = (CustomMultiplayerCard) __instance.hoveredCard;
                    Method playCardMethod = AbstractPlayer.class.getDeclaredMethod("playCard", new Class[0]);
                    playCardMethod.setAccessible(true);
                    ArrayList<P2PPlayer> availableAllies = new ArrayList<>(SpireHelp.Multiplayer.Players.GetPlayers(true, true));
                    if (
                            (clickedCard.getAllyTargetingRule() == CustomMultiplayerCard.AllyCardTargeting.ALLY_ONLY && availableAllies.size() == 1) ||
                            (clickedCard.getAllyTargetingRule() == CustomMultiplayerCard.AllyCardTargeting.ALLY_AND_ENEMY && availableAllies.size() == 1) ||
                            (clickedCard.getAllyTargetingRule() == CustomMultiplayerCard.AllyCardTargeting.ALLY_OR_SELF && availableAllies.isEmpty())
                    ) {
                        AbstractCreature hoveredCreature;
                        if (availableAllies.isEmpty()) {
                            hoveredCreature = player;
                        } else {
                            hoveredCreature = availableAllies.get(0).GetEntity();
                        }
                        Field field = AbstractPlayer.class.getDeclaredField("hoveredMonster");
                        field.setAccessible(true);
                        field.set(__instance, hoveredCreature);
                        playCardMethod.invoke(__instance);
                    }
                } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    System.out.println("------- Unsuccessful Auto-playing of TiS Card --------------------");
                }
        }
    }
}

