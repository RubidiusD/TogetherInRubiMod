package hegemonpack.powers;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static rubimod.powers.debuff.Sin.calculateSin;

public class Sinner extends BasePower {
    public static final String POWER_ID = ("HegemonPack:" + Sinner.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public Sinner(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(-1);
        amount = -1;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new Sinner(owner);
    }

    @SpirePatch2(clz= PoisonLoseHpAction.class, method="update", paramtypez = {})
    public static class PoisonSinnerPatch {
        @SpireInsertPatch(rloc=16)
        public static void Insert(PoisonLoseHpAction __instance) {
            if (__instance.target.hasPower(Sinner.POWER_ID))
                __instance.amount = calculateSin(__instance.target,  __instance.amount);
        }
    }
}
