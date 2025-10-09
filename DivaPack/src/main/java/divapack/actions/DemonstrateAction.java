package divapack.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import divapack.cards.skills.Imitate;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;
import spireTogether.util.SpireHelp;

public class DemonstrateAction extends AbstractGameAction {
    private final DamageInfo info;

    public DemonstrateAction(AbstractCreature target, DamageInfo info, int amount) {
        this.info = info;
        this.target = target;
    }

    @Override public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            this.target.damage(this.info);
            if (((this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                for (int index = 0; index != amount; index++) {
                    P2PPlayer player = SpireHelp.Multiplayer.Players.GetRandomPlayer(true, false);
                    if (player == null)
                        break;
                    AbstractCard imitate = new Imitate();
                    addToTop(new ShowCardAction(imitate));
                    player.addCard(NetworkCard.Generate(imitate), CardGroup.CardGroupType.MASTER_DECK);

                }
            }
        }

        this.tickDuration(); }
}
