package togetherinrubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.Ginger;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.util.SpireHelp;

public class MoraliseAction extends AbstractGameAction {
    AbstractPlayer owner;

    public MoraliseAction(AbstractPlayer owner) {this.owner = owner;}

    public void update()
    {
        int strength_gain = 0;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToTop(new DamageAction(m, new DamageInfo(owner, 0, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_LIGHT));
            if (m.hasPower(ArtifactPower.POWER_ID) && m.getPower(ArtifactPower.POWER_ID).amount > 0) {
                strength_gain += 1;
            }
            addToTop(new ApplyPowerAction(m, owner, new WeakPower(m, 1, false)));
        }

        for (P2PPlayer p : SpireHelp.Multiplayer.Players.GetPlayers(true, true)) {
            p.damage(new DamageInfo(owner, 0, DamageInfo.DamageType.NORMAL));
            if (p.hasPower(ArtifactPower.POWER_ID) || p.hasRelic(Ginger.ID)) {
                strength_gain += 1;
                p.addPower(new StrengthPower(owner, 2));
            }
            p.addPower(new WeakPower(owner, 1, false));
        }

        addToTop(new ApplyPowerAction(owner, owner, new StrengthPower(owner, strength_gain)));

        this.isDone = true;
    }
}
