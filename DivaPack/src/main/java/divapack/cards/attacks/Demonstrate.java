package divapack.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.actions.DemonstrateAction;
import divapack.cards.BaseCard;
import divapack.cards.skills.Imitate;
import dumbjokedivamod.character.Diva;

public class Demonstrate extends BaseCard {
    public static final String ID = ("DivaPack:" + Demonstrate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 15;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Demonstrate() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
        setExhaust(true);

        cardsToPreview = new Imitate();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DemonstrateAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), magicNumber));
    }

    @Override public void upgrade() {
        super.upgrade();
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    @Override public boolean canUpgrade() {
        return true;
    }

    @Override public AbstractCard makeCopy() { return new Demonstrate(); }
}
