package hegemonpack.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class JudgementPower extends BasePower {
    public static final String POWER_ID = ("HegemonPack:" + JudgementPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public JudgementPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToTop(new ReducePowerAction(owner, owner, this, 2));
    }

    @Override public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.magicNumber != -1) {
            card.magicNumber += amount;
        }
        addToTop(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override public float modifyBlock(float blockAmount, AbstractCard card) {
        return (card.magicNumber != -1 && card.damage != -1) ? blockAmount + amount : blockAmount;
    }

    @Override public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return (card.magicNumber != -1) ? damage + amount : damage;
    }

    @Override public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override public AbstractPower makeCopy() { return new JudgementPower(owner, amount); }
}
