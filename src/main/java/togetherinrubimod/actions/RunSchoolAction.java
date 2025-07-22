package togetherinrubimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import spireTogether.network.P2P.P2PPlayer;
import spireTogether.network.objects.items.NetworkCard;
import spireTogether.util.SpireHelp;

public class RunSchoolAction extends AbstractGameAction {
    private final boolean upgraded;

    public RunSchoolAction(boolean upgraded)
    {
        super();
        this.upgraded = upgraded;
    }

    @Override
    public void update()
    {
        AbstractCard c = new com.megacrit.cardcrawl.cards.tempCards.Insight();
        if (this.upgraded) {
            c.upgrade();
        }
        for (P2PPlayer e : SpireHelp.Multiplayer.Players.GetPlayers(true, true))
            e.addCard(NetworkCard.Generate(c), CardGroup.CardGroupType.DRAW_PILE);
        addToTop(new MakeTempCardInDrawPileAction(c, 1, true, true));

        this.isDone = true;
    }
}
