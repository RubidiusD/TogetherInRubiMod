package buxompack.cards.attacks;

import BuxomMod.characters.TheBuxom;
import BuxomMod.powers.ExposedPower;
import BuxomMod.powers.MilkPower;
import buxompack.cards.BaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class LiberalLactation extends BaseCard {
    public static final String ID = ("BuxomPack:" + LiberalLactation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public LiberalLactation() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        int amount = magicNumber;
        if (p.hasPower(ExposedPower.POWER_ID)) { amount *= 2; }
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
            e.addPower(new MilkPower(null, p, amount));
        }
    }

    @Override public AbstractCard makeCopy() { return new LiberalLactation(); }
}
