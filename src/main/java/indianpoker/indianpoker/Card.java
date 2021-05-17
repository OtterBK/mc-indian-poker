package indianpoker.indianpoker;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

public class Card {

    public static List<Card> cardList = new ArrayList<Card>();

    public static void Init(){
        for(int i = 0; i <= 12; i++){
            cardList.add(new Card(i, CardColor.WHITE));
            cardList.add(new Card(i, CardColor.BLUE));
            cardList.add(new Card(i, CardColor.YELLOW));
            cardList.add(new Card(i, CardColor.GREEN));
        }
    }

    public static Card getCardType(ItemStack item){
        for(Card card : cardList){
            if(card.cardItem.equals(item)){
                return card;
            }
        }
        return null;
    }

    public int cardNum = -1;
    public CardColor cardColor = CardColor.WHITE;

    public ItemStack cardItem;
    public DyeColor baseColor;
    public DyeColor fontColor;

    public Card(int cardNum, CardColor cardColor){

        this.cardNum = cardNum;
        this.cardColor = cardColor;

        baseColor = getDyeColor();
        fontColor = DyeColor.BLACK;

        cardItem = createItem();
    }

    public void toHat(Player p){
        p.getInventory().setHelmet(null);

        p.getInventory().setHelmet(cardItem);
        p.sendMessage("§a머리에 카드를 올렸습니다.");
    }

    public String getColorText(){
        if (cardColor == CardColor.WHITE) {
            return "white_banner";
        } else if (cardColor == CardColor.BLUE) {
            return "light_blue_banner";
        } else if (cardColor == CardColor.YELLOW) {
            return "yellow_banner";
        } else if (cardColor == CardColor.GREEN) {
            return "lime_banner";
        }

        return "white_banner";
    }

    public String getPatterText(){
        if(cardNum == -1) {
            return "{Patterns:[{Pattern:rs,Color:"+fontColor+"},{Pattern:hhb,Color:"+baseColor+"},{Pattern:ts,Color:"+fontColor+"},{Pattern:ms,Color:"+fontColor+"},{Pattern:bl,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 0) {
            return "{Patterns:[{Pattern:bs,Color:"+fontColor+"},{Pattern:ls,Color:"+fontColor+"},{Pattern:ts,Color:"+fontColor+"},{Pattern:rs,Color:"+fontColor+"},{Pattern:dls,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 1){
            if(cardColor == CardColor.WHITE){
                return "{Patterns:[{Pattern:rs,Color:"+fontColor+"},{Pattern:ls,Color:"+fontColor+"},{Pattern:ms,Color:"+fontColor+"},{Pattern:ts,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
            } else {
                return "{Patterns:[{Pattern:cs,Color:"+fontColor+"},{Pattern:tl,Color:"+fontColor+"},{Pattern:cbo,Color:"+baseColor+"},{Pattern:bs,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
            }
        } else if(cardNum == 2){
            return "{Patterns:[{Pattern:ts,Color:"+fontColor+"},{Pattern:mr,Color:"+baseColor+"},{Pattern:bs,Color:"+fontColor+"},{Pattern:dls,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 3){
            return "{Patterns:[{Pattern:bs,Color:"+fontColor+"},{Pattern:ms,Color:"+fontColor+"},{Pattern:ts,Color:"+fontColor+"},{Pattern:cbo,Color:"+baseColor+"},{Pattern:rs,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 4){
            return "{Patterns:[{Pattern:ls,Color:"+fontColor+"},{Pattern:hhb,Color:"+baseColor+"},{Pattern:rs,Color:"+fontColor+"},{Pattern:ms,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 5){
            return "{Patterns:[{Pattern:bs,Color:"+fontColor+"},{Pattern:mr,Color:"+baseColor+"},{Pattern:ts,Color:"+fontColor+"},{Pattern:drs,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 6){
            return "{Patterns:[{Pattern:rs,Color:"+fontColor+"},{Pattern:hh,Color:"+baseColor+"},{Pattern:bs,Color:"+fontColor+"},{Pattern:ls,Color:"+fontColor+"},{Pattern:ms,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 7){
            return "{Patterns:[{Pattern:dls,Color:"+fontColor+"},{Pattern:ts,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 8){
            return "{Patterns:[{Pattern:ts,Color:"+fontColor+"},{Pattern:ls,Color:"+fontColor+"},{Pattern:ms,Color:"+fontColor+"},{Pattern:bs,Color:"+fontColor+"},{Pattern:rs,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 9){
            return "{Patterns:[{Pattern:ls,Color:"+fontColor+"},{Pattern:hhb,Color:"+baseColor+"},{Pattern:ms,Color:"+fontColor+"},{Pattern:ts,Color:"+fontColor+"},{Pattern:rs,Color:"+fontColor+"},{Pattern:bs,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 10){ //j
            return "{Patterns:[{Pattern:ls,Color:"+fontColor+"},{Pattern:hh,Color:"+baseColor+"},{Pattern:bs,Color:"+fontColor+"},{Pattern:rs,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 11){ //x
            return "{Patterns:[{Pattern:cr,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        } else if(cardNum == 12){ //k
            return "{Patterns:[{Pattern:drs,Color:"+fontColor+"},{Pattern:hh,Color:"+baseColor+"},{Pattern:dls,Color:"+fontColor+"},{Pattern:ls,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
        }
        return "{Patterns:[{Pattern:rs,Color:"+fontColor+"},{Pattern:hhb,Color:"+baseColor+"},{Pattern:ts,Color:"+fontColor+"},{Pattern:ms,Color:"+fontColor+"},{Pattern:bl,Color:"+fontColor+"},{Pattern:bo,Color:"+baseColor+"}]}";
    }

    public Material getBannerMaterial(){
        if (cardColor == CardColor.WHITE) {
            return Material.WHITE_BANNER;
        } else if (cardColor == CardColor.BLUE) {
            return Material.BLUE_BANNER;
        } else if (cardColor == CardColor.YELLOW) {
            return Material.YELLOW_BANNER;
        } else if (cardColor == CardColor.GREEN) {
            return Material.GREEN_BANNER;
        }

        return Material.WHITE_BANNER;
    }

    public DyeColor getDyeColor(){
        if (cardColor == CardColor.WHITE) {
            return DyeColor.WHITE;
        } else if (cardColor == CardColor.BLUE) {
            return DyeColor.BLUE;
        } else if (cardColor == CardColor.YELLOW) {
            return DyeColor.YELLOW;
        } else if (cardColor == CardColor.GREEN) {
            return DyeColor.GREEN;
        }

        return DyeColor.WHITE;
    }

    public ItemStack createItem(){
        ItemStack banner = new ItemStack(getBannerMaterial());
        BannerMeta m = (BannerMeta)banner.getItemMeta();
        m.setDisplayName("§7카드 §f[ §a"+cardNum+" §f]");

        m.setBaseColor(getDyeColor());

        List<Pattern> patterns = getPatternList();

        m.setPatterns(patterns);

        banner.setItemMeta(m);

        return banner;
    }

    public List<Pattern> getPatternList(){
        List<Pattern> patterns = new ArrayList<Pattern>(); //Create a new List called 'patterns'


        if(cardNum == -1){
            patterns.add(new Pattern(baseColor, PatternType.BASE));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));
            patterns.add(new Pattern(baseColor, PatternType.HALF_HORIZONTAL_MIRROR));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_MIDDLE));
            patterns.add(new Pattern(fontColor, PatternType.SQUARE_BOTTOM_LEFT));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 0){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_LEFT));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_DOWNLEFT));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 1){
            if(cardColor == CardColor.WHITE){
                patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));
                patterns.add(new Pattern(fontColor, PatternType.STRIPE_LEFT));
                patterns.add(new Pattern(fontColor, PatternType.STRIPE_MIDDLE));
                patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));

                patterns.add(new Pattern(baseColor, PatternType.BORDER));
            } else {
                patterns.add(new Pattern(fontColor, PatternType.STRIPE_CENTER));
                patterns.add(new Pattern(fontColor, PatternType.SQUARE_TOP_LEFT));
                patterns.add(new Pattern(baseColor, PatternType.CURLY_BORDER));
                patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));

                patterns.add(new Pattern(baseColor, PatternType.BORDER));
            }
        } else if(cardNum == 2){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));
            patterns.add(new Pattern(baseColor, PatternType.RHOMBUS_MIDDLE));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_DOWNLEFT));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 3){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_MIDDLE));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));
            patterns.add(new Pattern(baseColor, PatternType.CURLY_BORDER));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 4){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_LEFT));
            patterns.add(new Pattern(baseColor, PatternType.HALF_HORIZONTAL_MIRROR));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_MIDDLE));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 5){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));
            patterns.add(new Pattern(baseColor, PatternType.RHOMBUS_MIDDLE));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_DOWNRIGHT));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 6){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));
            patterns.add(new Pattern(baseColor, PatternType.HALF_HORIZONTAL));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_LEFT));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_MIDDLE));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 7){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_DOWNLEFT));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 8){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_LEFT));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_MIDDLE));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 9){
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_LEFT));
            patterns.add(new Pattern(baseColor, PatternType.HALF_HORIZONTAL_MIRROR));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_MIDDLE));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_TOP));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 10){ //j
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_LEFT));
            patterns.add(new Pattern(baseColor, PatternType.HALF_HORIZONTAL));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_BOTTOM));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_RIGHT));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 11){ //x
            patterns.add(new Pattern(fontColor, PatternType.CROSS));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        } else if(cardNum == 12){ //k
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_DOWNRIGHT));
            patterns.add(new Pattern(baseColor, PatternType.HALF_HORIZONTAL));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_DOWNLEFT));
            patterns.add(new Pattern(fontColor, PatternType.STRIPE_LEFT));

            patterns.add(new Pattern(baseColor, PatternType.BORDER));
        }

        return patterns;

    }

    public void summonBanner(Location l, int rotate) {
        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setblock " + x + " " + y + " " + z + " " + "minecraft:" + getColorText() + "[rotation=" + rotate + "]" + getPatterText());
    }
}



enum CardColor {

    WHITE,BLUE,YELLOW,GREEN
}
