package divapack.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import divapack.cards.BaseCard;
import dumbjokedivamod.cards.starting.Defend;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

public class Imitate extends BaseCard {
    public static final String ID = ("DivaPack:" + Imitate.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0    // card cost!! (-1 is X, -2 is unplayable)
    );

    public static final int MAGIC = 3;
    public static final int UPG_MAGIC = 2;

    public Imitate() {
        super(ID, info); // calls the parent constructor

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override public void update() {
                ArrayList<AbstractCard> cards = new ArrayList<>(p.masterDeck.group);
                for (int i = 0; i < cards.size(); i++) {
                    if (cards.get(i).uuid == uuid) {
                        p.masterDeck.removeCard(cards.get(i));
                    }
                    if (cards.get(i).block == -1) {
                        cards.remove(i);
                        i -= 1;
                    }
                }

                if (cards.isEmpty()) {
                    AbstractCard pity = new Defend();
                    pity.block += magicNumber;
                    addToTop(new AddCardToDeckAction(pity));
                } else {
                    cards.get(random.nextInt(cards.size())).block += magicNumber;
                }

                isDone = true;
            }
        });
    }

    @Override public void triggerWhenDrawn() {
        addToTop(new DrawCardAction(1));
    }

    @Override public void upgrade() {
        super.upgrade();
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    @Override public boolean canUpgrade() {
        return true;
    }

    @Override public AbstractCard makeCopy() { return new Imitate(); }
}
