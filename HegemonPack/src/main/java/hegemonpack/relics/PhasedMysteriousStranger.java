package hegemonpack.relics;

import HegemonMod.character.Hegemon;
import HegemonMod.powers.buff.MysteriousPower;
import HegemonMod.relics.MysteriousStranger;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import dLib.util.PersistentActionManager;
import tisCardPack.TiSCardPack;
import tisCardPack.actions.RemoveRelicAction;

public class PhasedMysteriousStranger extends BaseRelic {
    private static final String NAME = PhasedMysteriousStranger.class.getSimpleName();
    public static final String ID = ("HegemonMod:" + NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.HEAVY;

    public PhasedMysteriousStranger() {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
        this.global = true;
    }

    @Override public void onEquip() {
        super.onEquip();
        UnlockTracker.markRelicAsSeen(ID);
        PersistentActionManager.get(TiSCardPack.getModID()).addToTop(new RemoveRelicAction(MysteriousStranger.ID));
    }

    @Override public void atBattleStart() {
        this.flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MysteriousPower(AbstractDungeon.player, 1)));
    }

    @Override public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(MysteriousStranger.ID);
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
