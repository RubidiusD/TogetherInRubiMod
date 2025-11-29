package hegemonpack.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.stances.AbstractStance;
import hegemonpack.stances.EclipseStance;
import hegemonpack.stances.SinStance;
import hegemonpack.stances.VirtueStance;

@SpirePatch2(clz= AbstractStance.class, method= "getStanceFromName", paramtypez = {String.class})
public class CreateStancePatch {
    @SpireInsertPatch(rloc= 9) public static SpireReturn<AbstractStance> Insert(String name) {
        if (name.equals(VirtueStance.STANCE_ID)) {
            return SpireReturn.Return(new VirtueStance());
        }
        if (name.equals(SinStance.STANCE_ID)) {
            return SpireReturn.Return(new SinStance());
        }
        if (name.equals(EclipseStance.STANCE_ID)) {
            return SpireReturn.Return(new EclipseStance());
        }
        return SpireReturn.Continue();
    }
}
