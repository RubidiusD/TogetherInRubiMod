package hegemonpack.cards.arbiter.skills.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.actions.AllyBlockAction;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;
import hegemonpack.powers.JudgementPower;

public class Cower extends BaseCard {
    public static final String ID = ("HegemonPack:" + Cower.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 1;

    public Cower() {
        super(ID, info); // calls the parent

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
        setAllyTargetingRule(AllyCardTargeting.ALLY_ONLY);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AllyBlockAction(m, block));
        addToBot(new ApplyPowerAction(p, p, new JudgementPower(p, 1)));
    }

    @Override public AbstractCard makeCopy() { return new Cower(); }
}
