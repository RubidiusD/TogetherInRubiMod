package divapack.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import dumbjokedivamod.character.Diva;
import dumbjokedivamod.powers.CaptivationPower;

public class Counterpoint extends BaseCard {
    public static final String ID = ("DivaPack:" + Counterpoint.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public Counterpoint() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (!p.chosenClass.equals(Diva.Meta.DIVA)) {
            addToBot(new ApplyPowerAction(m, p, new CaptivationPower(m, magicNumber)));
        }
    }

    @Override public AbstractCard makeCopy() { return new Counterpoint(); }
}
