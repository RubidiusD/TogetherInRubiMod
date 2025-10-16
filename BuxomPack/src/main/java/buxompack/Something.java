package buxompack;

public class Something {
    public static void PlayCard(com.megacrit.cardcrawl.characters.AbstractPlayer p) throws NoSuchMethodException, NoSuchFieldException, java.lang.reflect.InvocationTargetException, IllegalAccessException {

        if (p.hoveredCard instanceof spireTogether.cards.CustomMultiplayerCard) {
            spireTogether.cards.CustomMultiplayerCard clickedCard = (spireTogether.cards.CustomMultiplayerCard) p.hoveredCard;
            java.lang.reflect.Method playCardMethod = com.megacrit.cardcrawl.characters.AbstractPlayer.class.getDeclaredMethod("playCard", new Class[0]);
            playCardMethod.setAccessible(true);
            java.util.ArrayList<spireTogether.network.P2P.P2PPlayer> availableAllies = new java.util.ArrayList<>(spireTogether.util.SpireHelp.Multiplayer.Players.GetPlayers(true, true));
            if (
                    (clickedCard.getAllyTargetingRule() == spireTogether.cards.CustomMultiplayerCard.AllyCardTargeting.ALLY_ONLY && availableAllies.size() == 1) ||
                    (clickedCard.getAllyTargetingRule() == spireTogether.cards.CustomMultiplayerCard.AllyCardTargeting.ALLY_AND_ENEMY && availableAllies.size() == 1) ||
                    (clickedCard.getAllyTargetingRule() == spireTogether.cards.CustomMultiplayerCard.AllyCardTargeting.ALLY_OR_SELF && availableAllies.isEmpty())
            ) {
                com.megacrit.cardcrawl.core.AbstractCreature hoveredCreature;
                if (availableAllies.isEmpty()) {
                    hoveredCreature = p;
                } else {
                    hoveredCreature = availableAllies.get(0).GetEntity();
                }
                java.lang.reflect.Field field = com.megacrit.cardcrawl.characters.AbstractPlayer.class.getDeclaredField("hoveredMonster");
                field.setAccessible(true);
                field.set(p, hoveredCreature);
                playCardMethod.invoke(p, new Object[0]);
            }
        }

    }
}
