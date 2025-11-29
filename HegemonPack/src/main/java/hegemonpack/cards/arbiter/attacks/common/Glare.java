package hegemonpack.cards.arbiter.attacks.common;

import HegemonMod.powers.debuff.Sin;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;

public class Glare extends BaseCard {
    public static final String ID = ("HegemonPack:" + Glare.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 1;
    private static final int UPG_DAMAGE = 1;
    private static final int BLOCK = 6;

    public Glare() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(m, p, new Sin(m, damage)));
    }

    @Override public AbstractCard makeCopy() { return new Glare(); }
}
