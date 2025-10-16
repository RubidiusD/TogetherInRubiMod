package divapack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import divapack.powers.CheerPower;
import dumbjokedivamod.powers.CaptivationPower;
import dumbjokedivamod.powers.RhythmPower;
import spireTogether.network.P2P.P2PManager;
import spireTogether.subscribers.TiSNetworkMessageSubscriber;
import spireTogether.util.NetworkMessage;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class MessageReceiver implements TiSNetworkMessageSubscriber {
    @Override public void onMessageReceive(NetworkMessage networkMessage, String request, Object o, Integer senderID) {
        if (request.equals(captivationTrigger)) {
            if (player.hasPower(CaptivationPower.POWER_ID)) {
                player.getPower(CaptivationPower.POWER_ID).onSpecificTrigger();
            }
            if (player.hasPower(CheerPower.POWER_ID)) {
                player.getPower(CheerPower.POWER_ID).onSpecificTrigger();
            }
        }
    }

    // ------------------------------------------------------------------------Message Sender-----------------

    public static String captivationTrigger = "captivationTrigger";

    @SpirePatch2(clz= RhythmPower.class, method= "updateCaptivation", paramtypez = {})
    public static class CaptivationPatch {
        @SpirePostfixPatch
        public static void Postfix() {
            P2PManager.SendData(new NetworkMessage(captivationTrigger));
        }
    }
}
