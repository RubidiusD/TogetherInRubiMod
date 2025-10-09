package hegemonpack.cards.powers;

import HegemonMod.cards.attacks.Punish;
import HegemonMod.character.Hegemon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hegemonpack.cards.BaseCard;
import hegemonpack.powers.AvengerPower;

public class Avenger extends BaseCard {
    public static final String ID = ("HegemonPack:" + Avenger.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 1;

    public Avenger() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC); // self-explanatory
        setEthereal(true);
        setInnate(false, true);

        cardsToPreview = new Punish();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AvengerPower(p, 1)));
    }

    @Override public AbstractCard makeCopy() { return new Avenger(); }
}
