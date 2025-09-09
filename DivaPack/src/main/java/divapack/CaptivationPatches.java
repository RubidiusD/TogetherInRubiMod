package divapack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dumbjokedivamod.powers.CaptivationPower;
import dumbjokedivamod.powers.RhythmPower;
import spireTogether.network.P2P.P2PManager;
import spireTogether.network.P2P.P2PMessageAnalyzer;
import spireTogether.util.NetworkMessage;

public class CaptivationPatches {
    public static String captivationTrigger = "captivationTrigger";

    @SpirePatch2(clz= RhythmPower.class, method= "updateCaptivation", paramtypez = {})
    public static class CaptivationPatch {
        @SpirePostfixPatch public static void Postfix() {
            P2PManager.SendData(new NetworkMessage(captivationTrigger));
        }
    }

    @SpirePatch2(clz= P2PMessageAnalyzer.class, method= "AnalyzeMessage", paramtypez = {NetworkMessage.class})
    public static class AnalyzeMessagePatch {
        @SpirePostfixPatch public static void Postfix(NetworkMessage data) {
            if (data.request.equals(captivationTrigger)) {
                if (AbstractDungeon.player.hasPower(CaptivationPower.POWER_ID)) {
                    AbstractDungeon.player.getPower(CaptivationPower.POWER_ID).onSpecificTrigger();
                }
            }
        }
    }
}
