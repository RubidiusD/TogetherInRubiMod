package hegemonpack.cards.arbiter.attacks.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;

import static HegemonMod.util.CustomTags.NECROTIC;

public class SpeedChant extends BaseCard {
    public static final String ID = ("HegemonPack:" + SpeedChant.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 1;

    public SpeedChant() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);

        tags.add(NECROTIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.POISON));
        addToBot(new ApplyPowerAction(p, p, new BlurPower(p, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new SpeedChant(); }
}
