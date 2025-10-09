package buxompack.cards.attacks;

import BuxomMod.characters.TheBuxom;
import BuxomMod.powers.LactatingPower;
import buxompack.cards.BaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlushingStrike extends BaseCard {
    public static final String ID = ("BuxomPack:" + BlushingStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardRarity.COMMON,
            AbstractCard.CardTarget.ENEMY,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 2;

    public BlushingStrike() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory

        tags.add(CardTags.STRIKE);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (!p.chosenClass.equals(TheBuxom.Enums.THE_BUXOM)) {
            addToBot(new ApplyPowerAction(p, p, new LactatingPower(p, p, magicNumber)));
        }
    }

    @Override public AbstractCard makeCopy() { return new BlushingStrike(); }
}
