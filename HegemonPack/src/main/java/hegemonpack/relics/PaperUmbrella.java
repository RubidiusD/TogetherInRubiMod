package hegemonpack.relics;

import HegemonMod.character.Hegemon;
import HegemonMod.powers.debuff.Sin;
import HegemonMod.relics.MysteriousStranger;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import dLib.util.PersistentActionManager;
import tisCardPack.TiSCardPack;
import tisCardPack.actions.RemoveRelicAction;

public class PaperUmbrella extends BaseRelic {
    private static final String NAME = PaperUmbrella.class.getSimpleName();
    public static final String ID = ("HegemonMod:" + NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public static final float StrengthModifier = 0.03f;

    public PaperUmbrella() {
        super(ID, NAME, Hegemon.Meta.CARD_COLOR, RARITY, SOUND);
        this.global = true;
    }

    @Override public void onEquip() {
        super.onEquip();
        UnlockTracker.markRelicAsSeen(ID);
        Sin.Strength += StrengthModifier;
        PersistentActionManager.get(TiSCardPack.getModID()).addToTop(new RemoveRelicAction(MysteriousStranger.ID));
    }

    @Override public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
