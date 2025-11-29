package hegemonpack.cards.arbiter.attacks.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;

public class Overrule extends BaseCard {
    public static final String ID = ("HegemonPack:" + Overrule.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;

    public Overrule() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE);
        setInnate(true);
        setExhaust(true);
    }

    @Override public void upgrade() {
        super.upgrade();
        this.target = CardTarget.ALL_ENEMY;
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        if (timesUpgraded == 0) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            addToBot(new RemoveSpecificPowerAction(m, m, ArtifactPower.POWER_ID));
        } else {
            addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                addToBot(new RemoveSpecificPowerAction(mo, mo, ArtifactPower.POWER_ID));
            }
        }
    }

    @Override public AbstractCard makeCopy() { return new Overrule(); }
}
