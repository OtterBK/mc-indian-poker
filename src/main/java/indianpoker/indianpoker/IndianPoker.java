package indianpoker.indianpoker;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class IndianPoker extends JavaPlugin implements Listener {

    private CardDeck indianPokerDeck;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Card.Init();
        Bukkit.getLogger().info("인디안 포커 플러그인 로드됨");
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("인디안 포커 플러그인 언로드됨");
    }

    public void prepareDeck(Player p){
        //Bukkit.broadcastMessage("§a인디언 포커 시작");

        CardDeck cardDeck = new CardDeck(p.getLocation().clone().add(0,1,0));
        for(int i = 0; i < 10; i++){
            cardDeck.add(new Card(i, CardColor.WHITE), true);
            cardDeck.add(new Card(i, CardColor.YELLOW), true);
        }
        Bukkit.broadcastMessage("§a카드 덱 생성");

        cardDeck.shuffle();
        Bukkit.broadcastMessage("§a덱 셔플");

        indianPokerDeck = cardDeck;
        Bukkit.broadcastMessage("§a덱 준비 완료");
    }

    @EventHandler
    public void onCommandInput(PlayerCommandPreprocessEvent e) {
        String[] args = e.getMessage().split(" ");
        String cmd = args[0];

        Player p = e.getPlayer();

        if (cmd.equalsIgnoreCase("/카드") && p.isOp()) {
            if (args.length == 1) {

                p.sendMessage("");

            } else{
                if(args[1].equalsIgnoreCase("테스트")){
                    p.sendMessage("test");
                    for(int i = 0; i <= 13; i++){
                        Card card = new Card(i, CardColor.BLUE);
                        p.getInventory().addItem(card.cardItem);
                    }




                } if(args[1].equalsIgnoreCase("인디언포커")){
                    if(args.length == 2){
                        p.sendMessage("§a/카드 인디언포커 덱");
                        p.sendMessage("§a/카드 인디언포커 진행");
                    } else {
                        if(args[2].equalsIgnoreCase("덱")){

                            prepareDeck(p);

                        } else if(args[2].equalsIgnoreCase("진행")) {
                            if(indianPokerDeck == null){
                                p.sendMessage("§a덱을 먼저 생성해주세요.");
                            } else {

                                int playerSize = 0;
                                for(Player onP : Bukkit.getOnlinePlayers()) {
                                    if (onP.getGameMode() == GameMode.SPECTATOR) continue; // 관전자 제외
                                    playerSize++;
                                }

                                if(indianPokerDeck.getDeckSize() < playerSize){
                                    p.sendMessage("§a덱이 부족합니다. \n새로운 덱을 준비합니다.");
                                    prepareDeck(p);
                                }

                                for(Player onP : Bukkit.getOnlinePlayers()){
                                    if(onP.getGameMode() == GameMode.SPECTATOR) continue; // 관전자 제외
                                    Card card = indianPokerDeck.draw();
                                    //handCardMap.put(p.getUniqueId().toString(), card);
                                    card.toHat(onP);
                                    //card.summonBanner(onP.getLocation().add(0,4,0), Utility.getRotateValue(onP.getLocation()));
                                    onP.playSound(onP.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 3.0f, 0.35f);
                                    onP.sendTitle("", "§a§l카드를 받았습니다.", 1, 60, 1);
                                }
                            }
                        }
                    }


                }
            }
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getClickedBlock() != null){
            Block b = e.getClickedBlock();
            if(b.getType() == CardDeck.cardDeckBlock){
                CardDeck cardDeck = CardDeck.findCardDeck(b.getLocation());
                if(cardDeck != null){
                    if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
                        Card card = cardDeck.draw(p);
                    } else if(e.getAction() == Action.LEFT_CLICK_BLOCK){
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if(item != null){
                            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                                String itemName = item.getItemMeta().getDisplayName();
                                if (itemName.contains("카드")) {
                                    Card handCard = Card.getCardType(p.getInventory().getItemInMainHand());
                                    cardDeck.add(handCard, true);
                                    p.sendMessage("§a카드를 제출했습니다.");
                                }
                            }
                        }
                    }
                    e.setCancelled(true);
                }
            }
        }
    }

}
