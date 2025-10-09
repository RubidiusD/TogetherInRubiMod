package hegemonpack.cards.attacks;

import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;
import spireTogether.monsters.CharacterEntity;
import tisCardPack.powers.InsanityPower;
import hegemonpack.cards.BaseCard;

public class Untether extends BaseCard {
    public static final String ID = ("HegemonPack:" + Untether.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            0 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 0;

    public Untether() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE);
        setAllyTargetingRule(AllyCardTargeting.ALLY_ONLY);
        setExhaust(true, false);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        if (m instanceof CharacterEntity) {
            CharacterEntity ce = (CharacterEntity) m;
            ce.addPower(new DrawPower(ce, 1));
            ce.addPower(new InsanityPower(ce, p, 3));
            ce.damage(new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL));
        }
    }

    @Override public AbstractCard makeCopy() { return new Untether(); }
}
