package hegemonpack.cards.arbiter.attacks.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.cards.arbiter.skills.Soothe;
import hegemonpack.characters.Arbiter;

import static HegemonMod.util.CustomTags.NECROTIC;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent.*;

public class Spacer extends BaseCard {
    public static final String ID = ("HegemonPack:" + Spacer.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Arbiter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 5;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Spacer() {
        super(ID, info); // calls the parent

        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        cardsToPreview = new Soothe();

        tags.add(NECROTIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.POISON));
        if (m.intent == ATTACK || m.intent == ATTACK_BUFF || m.intent == ATTACK_DEBUFF || m.intent == ATTACK_DEFEND) {
            addToBot(new MakeTempCardInDrawPileAction(cardsToPreview, magicNumber, true, true));
        }
    }

    @Override public AbstractCard makeCopy() { return new Spacer(); }
}
