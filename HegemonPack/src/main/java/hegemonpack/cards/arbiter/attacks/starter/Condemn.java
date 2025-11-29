package hegemonpack.cards.arbiter.attacks.starter;

import HegemonMod.powers.debuff.Necrotoxin;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.arbiter.optioncards.ChooseSin;
import hegemonpack.characters.Arbiter;
import hegemonpack.stances.SinStance;
import hegemonpack.cards.BaseCard;

public class Condemn extends BaseCard {
    public static final String ID = ("HegemonPack:" + Condemn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ALL_ENEMY,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;

    public Condemn() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE);
        setCostUpgrade(1);

        cardsToPreview = new ChooseSin();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AllEnemyApplyPowerAction(p, damage, (AbstractMonster mo) -> (new Necrotoxin(mo, p, damage))));
        addToBot(new ChangeStanceAction(new SinStance()));
    }

    @Override public AbstractCard makeCopy() { return new Condemn(); }
}
