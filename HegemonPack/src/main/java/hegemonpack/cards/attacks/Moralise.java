package hegemonpack.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rubimod.character.Hegemon;
import hegemonpack.actions.MoraliseAction;
import hegemonpack.cards.BaseCard;
import hegemonpack.util.CardStats;

public class Moralise extends BaseCard {
    public static final String ID = ("HegemonPack:" + Moralise.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 0;

    public Moralise() {
        super(ID, info); // calls the parent

        setCostUpgrade(0);
        setDamage(DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MoraliseAction(p));
        }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new Moralise();
    }
}
