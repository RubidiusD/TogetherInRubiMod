package hegemonpack.patches;

import HegemonMod.character.Hegemon;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import spireTogether.monsters.playerChars.NetworkDefaultChar;
import spireTogether.util.TextureManager;

@SpirePatch2(clz= NetworkDefaultChar.class, method= "GetDefaultIcon", paramtypez = {})
public class IconPatch {
    @SpirePostfixPatch public static Texture Postfix(Texture __result, NetworkDefaultChar __instance) {
        if (__instance.source.chosenClass == Hegemon.Meta.HEGEMON) {
            return TextureManager.getTexture("hegemonpack/images/HegemonIcon.png");
        }
        return __result;
    }
}
