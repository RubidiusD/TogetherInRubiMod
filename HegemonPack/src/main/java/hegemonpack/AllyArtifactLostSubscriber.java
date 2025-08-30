package hegemonpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireTogether.network.P2P.P2PManager;
import spireTogether.network.P2P.P2PMessageAnalyzer;
import spireTogether.util.NetworkMessage;

public interface AllyArtifactLostSubscriber {
    void receiveAllyArtifactLost();

    String artifactTrigger = "HegemonPack:" + "artifactTrigger";

    @SpirePatch2(clz = ArtifactPower.class, method = "onSpecificTrigger", paramtypez = {})
    class ArtifactPatch {
        @SpirePostfixPatch public static void Postfix() {
            P2PManager.SendData(new NetworkMessage(artifactTrigger));
        }
    }

    @SpirePatch2(clz= P2PMessageAnalyzer.class, method= "AnalyzeMessage", paramtypez = {NetworkMessage.class})
    class AnalyzeMessagePatch {
        @SpirePostfixPatch public static void Postfix(NetworkMessage data) {
            if (data.request.equals(artifactTrigger)) {
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
    }
}
