package buxompack.cards.skills;

import BuxomMod.BuxomMod;
import BuxomMod.characters.TheBuxom;
import BuxomMod.orbs.DefenseChibi;
import BuxomMod.powers.CommonPower;
import BuxomMod.powers.ExposedPower;
import buxompack.cards.BaseCard;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireTogether.monsters.CharacterEntity;

public class ChibiDeployment extends BaseCard {
    public static final String ID = ("BuxomPack:" + ChibiDeployment.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public ChibiDeployment() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);

        setAllyTargetingRule(AllyCardTargeting.ALLY_ONLY);
    }

    @Override public boolean freeToPlay() {
        return CardCrawlGame.isInARun() && AbstractDungeon.player.hasPower(ExposedPower.POWER_ID) && !BuxomMod.braManager.embarrassingList.contains(this.uuid) || super.freeToPlay();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        if (m instanceof CharacterEntity) {
            ((CharacterEntity)m).channelOrb(new DefenseChibi(), true);
        }
        addToBot(new ReducePowerAction(p, p, CommonPower.POWER_ID, this.magicNumber));

        BuxomMod.braManager.embarrassingList.add(this.uuid);
        BuxomMod.logger.info("embarrassingList: {}", BuxomMod.braManager.embarrassingList);
    }

    @Override public AbstractCard makeCopy() { return new ChibiDeployment(); }
}
