package togetherinrubimod.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import togetherinrubimod.powers.Sinner;

import static rubimod.powers.debuff.Sin.calculateSin;

@SpirePatch2(
        clz= PoisonLoseHpAction.class,
        method="update",
        paramtypez = {}
)
public class PoisonSinnerPatch {
    @SpireInsertPatch(rloc=16)
    public static void Insert(PoisonLoseHpAction __instance)
    {
        if (__instance.target.hasPower(Sinner.POWER_ID))
            __instance.amount = calculateSin(__instance.target, __instance.source,  __instance.amount);
    }
}
