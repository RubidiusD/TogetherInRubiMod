package divapack.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import divapack.powers.SkippedBeatsPower;
import dumbjokedivamod.character.Diva;

public class SkippedBeats extends BaseCard {
    public static final String ID = ("DivaPack:" + SkippedBeats.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Diva.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public SkippedBeats() {
        super(ID, info); // calls the parent constructor

        this.setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SkippedBeatsPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new SkippedBeats();
    }
}
