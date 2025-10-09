package divapack.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import dLib.util.PersistentActionManager;
import dumbjokedivamod.character.Diva;
import dumbjokedivamod.powers.RhythmPower;
import dumbjokedivamod.relics.starter.Microphone;
import tisCardPack.TiSCardPack;
import tisCardPack.actions.RemoveRelicAction;

public class PhasedMicrophone extends BaseRelic implements OnReceivePowerRelic {
    private static final String NAME = PhasedMicrophone.class.getSimpleName();
    public static final String ID = "DivaPack:" + NAME;
    private static final AbstractRelic.RelicTier RARITY = RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;

    public PhasedMicrophone() {
        super(ID, NAME, Diva.Meta.CARD_COLOR, RARITY, SOUND);
        this.global = true;
    }

    @Override public void onEquip() {
        super.onEquip();
        UnlockTracker.markRelicAsSeen(ID);
        PersistentActionManager.get(TiSCardPack.getModID()).addToTop(new RemoveRelicAction(Microphone.ID));
    }

    public boolean onReceivePower(AbstractPower power, AbstractCreature source) {
        if (power.ID.equals(RhythmPower.POWER_ID) && AbstractDungeon.player.hasPower(RhythmPower.POWER_ID)) {
            AbstractPower rhythm = AbstractDungeon.player.getPower(RhythmPower.POWER_ID);
            if (rhythm.amount >= 3) {
                this.flash();
                this.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, RhythmPower.POWER_ID));
            }
        }

        return true;
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
