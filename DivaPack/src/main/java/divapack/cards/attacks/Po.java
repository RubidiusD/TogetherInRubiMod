package divapack.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.CultistMask;
import divapack.cards.BaseCard;
import dumbjokedivamod.Actions.ApplyRhythmAction;
import dumbjokedivamod.character.Diva;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class Po extends BaseCard {
    public static final String ID = ("DivaPack:" + Po.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0    // card cost!! (-1 is X, -2 is unplayable)
    );

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 1;
    private static final int DRAW = 1;

    public Po() {
        super(ID, info); // calls the parent constructor

        setDamage(DAMAGE, UPG_DAMAGE); // self-explanatory
        setMagic(MAGIC); // self-explanatory
        setCustomVar("Draw", DRAW);

        if (random.nextBoolean())
            this.originalName = languagePack.getUIString(ID).TEXT[0];
        else
            this.originalName = languagePack.getUIString(ID).TEXT[1];
        this.name = originalName;
        initializeTitle();
    }

    @Override public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyRhythmAction());
        addToBot(new DrawCardAction(customVar("Draw")));
        addToBot(new TalkAction(true, this.name, 1.0F, 2.0F));
    }

    @Override protected void upgradeName() {
        String tmpName = this.name;
        super.upgradeName();
        if (random.nextBoolean())
            this.name = tmpName + languagePack.getUIString(ID).TEXT[0];
        else
            this.name = tmpName + languagePack.getUIString(ID).TEXT[1];
        initializeTitle();
    }

    @Override public boolean canUpgrade() {
        return true;
    }

    @Override public AbstractCard makeCopy() { return new Po(); }
}
