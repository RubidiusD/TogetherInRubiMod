package buxompack.cards.attacks;

import BuxomMod.characters.TheBuxom;
import BuxomMod.powers.CommonPower;
import buxompack.actions.TakeBuxomAction;
import buxompack.cards.BaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class OmegaEnvy extends BaseCard {
    public static final String ID = ("BuxomPack:" + OmegaEnvy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheBuxom.Enums.COLOR_PINK,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public OmegaEnvy() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
            if (!e.playerClass.equals(TheBuxom.Enums.THE_BUXOM)) {
                addToBot(new TakeBuxomAction(e));
            }
        }
    }

    @Override public AbstractCard makeCopy() { return new OmegaEnvy(); }
}
