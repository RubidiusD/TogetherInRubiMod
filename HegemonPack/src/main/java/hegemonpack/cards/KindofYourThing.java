package hegemonpack.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KindofYourThing extends BaseCard {
    public static final String ID = ("HegemonPack:" + KindofYourThing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.SPECIAL,
            CardTarget.ALL,
            -2 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 5;

    public KindofYourThing() {
        super(ID, info); // calls the parent

        setSelfRetain(true);
        setMagic(MAGIC, UPG_MAGIC);
        this.shuffleBackIntoDrawPile = true;
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            this.addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override public void triggerOnEndOfPlayerTurn() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    @Override public AbstractCard makeCopy() { return new KindofYourThing(); }
}
