package hegemonpack.cards.attacks;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import tisCardPack.powers.TauntPlayerPower;
import tisCardPack.powers.TauntPower;
import hegemonpack.cards.BaseCard;
import rubimod.character.Hegemon;
import tisCardPack.actions.ApplyTauntAction;

public class MouthEssence extends BaseCard {
    public static final String ID = ("HegemonPack:" + MouthEssence.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Hegemon.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 4;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public MouthEssence() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE); // self-explanatory
        setMagic(MAGIC, UPG_MAGIC); // self-explanatory
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyTauntAction(m, p, magicNumber));
        addToBot(new ApplyTauntAction(p, m, magicNumber));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }

    @Override
    public AbstractCard makeCopy() { // Optional
        return new MouthEssence();
    }

    @SpirePatch2(
            clz= TauntPower.class,
            method= SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCreature.class,
                    AbstractCreature.class,
                    int.class}
    )
    public static class TauntPatch {
        @SpireInsertPatch(rloc=59)
        public static void Insert(TauntPower __instance)
        {
            __instance.type = AbstractPower.PowerType.BUFF;
        }
    }

    @SpirePatch2(
            clz= TauntPlayerPower.class,
            method= SpirePatch.CONSTRUCTOR,
            paramtypez = {
                    AbstractCreature.class,
                    AbstractCreature.class,
                    int.class}
    )
    public static class TauntPlayerPatch {
        @SpireInsertPatch(rloc=62)
        public static void Insert(TauntPlayerPower __instance)
        {
            __instance.type = AbstractPower.PowerType.BUFF;
        }
    }
}
