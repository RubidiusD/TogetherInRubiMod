package hegemonpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireTogether.network.P2P.P2PManager;
import spireTogether.subscribers.TiSNetworkMessageSubscriber;
import spireTogether.util.NetworkMessage;

public class MessageReceiver implements TiSNetworkMessageSubscriber {
    static String artifactTrigger = "HegemonPack:" + "artifactTrigger";

    @Override public void onMessageReceive(NetworkMessage networkMessage, String request, Object o, Integer ID) {
        if (request.equals(artifactTrigger)) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof AllyArtifactLostSubscriber) {
                    ((AllyArtifactLostSubscriber) r).receiveAllyArtifactLost();
                }
            }

            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof AllyArtifactLostSubscriber) {
                    ((AllyArtifactLostSubscriber) p).receiveAllyArtifactLost();
                }
            }

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                for (AbstractPower p : m.powers) {
                    if (p instanceof AllyArtifactLostSubscriber) {
                        ((AllyArtifactLostSubscriber) p).receiveAllyArtifactLost();
                    }
                }
            }
        }
    }

    public interface AllyArtifactLostSubscriber {
        void receiveAllyArtifactLost();
    }

    // --------------------------------------------- Send Message ---------------------------

    @SpirePatch2(clz = ArtifactPower.class, method = "onSpecificTrigger", paramtypez = {})
    static class ArtifactPatch {
        @SpirePostfixPatch public static void Postfix() {
            P2PManager.SendData(new NetworkMessage(artifactTrigger));
        }
    }
}
