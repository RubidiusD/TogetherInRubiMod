package buxompack.cards.attacks;

import BuxomMod.characters.TheBuxom;
import buxompack.cards.BaseCard;
import buxompack.powers.VisualisePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StimSmash extends BaseCard {
    public static final String ID = ("BuxomPack:" + StimSmash.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public StimSmash() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ApplyPowerAction(m, p, new VisualisePower(m, magicNumber)));
    }

    @Override public AbstractCard makeCopy() { return new StimSmash(); }
}
