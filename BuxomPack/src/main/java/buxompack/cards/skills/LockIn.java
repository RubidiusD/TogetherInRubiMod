package buxompack.cards.skills;

import BuxomMod.BuxomMod;
import BuxomMod.cards.AftershockStatus;
import BuxomMod.cards.Rapidswell;
import BuxomMod.characters.TheBuxom;
import BuxomMod.powers.ExposedPower;
import buxompack.cards.BaseCard;
import buxompack.powers.VisualisePower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LockIn extends BaseCard {
    public static final String ID = ("BuxomPack:" + LockIn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public LockIn() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);

        cardsToPreview = new AftershockStatus();
    }

    @Override public boolean freeToPlay() {
        return CardCrawlGame.isInARun() && AbstractDungeon.player.hasPower(ExposedPower.POWER_ID) && !BuxomMod.braManager.embarrassingList.contains(this.uuid) || super.freeToPlay();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AllEnemyApplyPowerAction(p, 1, (AbstractMonster mo) -> new VisualisePower(mo, 1)));
        addToBot(new MakeTempCardInDrawPileAction(cardsToPreview, 2, true, true));

        BuxomMod.braManager.embarrassingList.add(this.uuid);
        BuxomMod.logger.info("embarrassingList: {}", BuxomMod.braManager.embarrassingList);
    }

    @Override public void upgrade() {
        super.upgrade();
        cardsToPreview = new Rapidswell();
    }

    @Override public AbstractCard makeCopy() { return new LockIn(); }
}
