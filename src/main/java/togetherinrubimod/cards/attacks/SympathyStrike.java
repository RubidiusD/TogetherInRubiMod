package togetherinrubimod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.character.Hegemon;
import rubimod.powers.buff.ShadowHand;
import togetherinrubimod.cards.BaseCard;
import togetherinrubimod.util.CardStats;

public class SympathyStrike extends BaseCard {
    public static final String ID = makeID(SympathyStrike.class.getSimpleName()); // makeID adds the mod name
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 3;

    public SympathyStrike() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC); // self-explanatory

        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (!p.chosenClass.equals(Hegemon.Meta.HEGEMON)) {
            addToBot(new ApplyPowerAction(p, p, new ShadowHand(p, magicNumber)));
        }
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new SympathyStrike();
    }
}
