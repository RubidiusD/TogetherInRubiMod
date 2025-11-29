package hegemonpack.cards.arbiter.attacks.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.characters.Arbiter;

import static HegemonMod.util.CustomTags.NECROTIC;

public class Wither extends BaseCard {
    public static final String ID = ("HegemonPack:" + Wither.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 5;

    public Wither() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE, UPG_DAMAGE);
        setEthereal(true);

        tags.add(NECROTIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.POISON));
    }

    @Override public AbstractCard makeCopy() { return new Wither(); }
}
