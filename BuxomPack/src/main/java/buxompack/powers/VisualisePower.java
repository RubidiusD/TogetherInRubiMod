package buxompack.powers;

import BuxomMod.BuxomMod;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class VisualisePower extends BasePower {
    public static final String POWER_ID = ("BuxomPack:" + VisualisePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public VisualisePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null) {
            CardGroup cards = BuxomMod.specialGetCardsOfType(player.hand, AbstractCard.CardType.STATUS);
            if (!cards.isEmpty()) {
                addToTop(new ExhaustSpecificCardAction(cards.getRandomCard(true), player.hand));
            }
        }

        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        reducePower(1);
        if (amount == 0) {
            addToTop(new RemoveSpecificPowerAction(owner, owner,this));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override public AbstractPower makeCopy() { return new VisualisePower(owner, amount); }
}
