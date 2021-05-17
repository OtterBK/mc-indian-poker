package indianpoker.indianpoker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    public static List<CardDeck> cardDecks = new ArrayList<CardDeck>();
    public static Material cardDeckBlock = Material.ANVIL;

    public List<Card> deck = new ArrayList<Card>();
    public Location deckLocaion;


    public CardDeck(Location l){

        cardDecks.add(this);

        deckLocaion = l;

    }

    public void createDeckBlock(){
        deckLocaion.getBlock().setType(cardDeckBlock);

        Card tmpCard = new Card(-1, CardColor.WHITE);
        tmpCard.summonBanner(deckLocaion.add(0,2,0), Utility.getRotateValue(deckLocaion));
    }

    public static CardDeck findCardDeck(Location l){
        for(CardDeck tmpDeck : CardDeck.cardDecks){
            Location deckLoc = tmpDeck.deckLocaion;
            if(deckLoc.getBlockX() == l.getBlockX() &&
                    deckLoc.getBlockY() == l.getBlockY() &&
                    deckLoc.getBlockZ() == l.getBlockZ()){
                return tmpDeck;
            }
        }
        return null;
    }

    public int getDeckSize(){
        return deck.size();
    }

    public void showTop(){
        Card card = getTop();
        card.summonBanner(deckLocaion, Utility.getRotateValue(deckLocaion));
    }

    public Card getTop(){
        if(deck.size() > 0){
            deckLocaion.getWorld().playSound(deckLocaion, Sound.ITEM_BOOK_PAGE_TURN, 3.0f, 0.35f);
            return deck.get(deck.size() - 1);
        }else{
            Bukkit.broadcastMessage("§a덱에 카드가 없습니다.");
            return null;
        }
    }

    public void add(Card card, boolean isTop){
        if(isTop){
            deck.add(card);
        } else {
            deck.add(0, card);
        }
    }

    public Card draw(){
        if(deck.size() > 0){
            deckLocaion.getWorld().playSound(deckLocaion, Sound.ITEM_BOOK_PAGE_TURN, 3.0f, 0.35f);
            return deck.remove(deck.size() - 1);
        }else{
            Bukkit.broadcastMessage("§a덱에 카드가 없습니다.");
            return null;
        }
    }

    public Card draw(Player p){
        if(deck.size() > 0){
            Card card = draw();
            p.getInventory().addItem(card.cardItem);
            p.sendMessage("§a카드를 뽑았습니다.");
            p.getWorld().playSound(deckLocaion, Sound.ITEM_BOOK_PAGE_TURN, 3.0f, 0.35f);
            return card;
        }else{
            Bukkit.broadcastMessage("§a덱에 카드가 없습니다.");
            return null;
        }
    }

    public void shuffle(){ // 카드 섞기
        if(deck.size() > 0){
            Collections.shuffle(deck);
            deckLocaion.getWorld().playSound(deckLocaion, Sound.ENTITY_BAT_TAKEOFF, 3.0f, 0.2f);
        }else{
            Bukkit.broadcastMessage("§a덱에 카드가 없습니다.");
            return;
        }
    }

}
