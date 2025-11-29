package hegemonpack.cards.arbiter.attacks.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;

public class TalkToTheBook extends BaseCard {
    public static final String ID = ("HegemonPack:" + TalkToTheBook.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 10;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public TalkToTheBook() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(m, p, new BlockReturnPower(m, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new TalkToTheBook(); }
}
