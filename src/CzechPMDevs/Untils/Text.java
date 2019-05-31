package CzechPMDevs.Untils;


import org.itxtech.nemisys.utils.TextFormat;

public class Text {

    public static Text instance;

    public Text(){
        instance = this;
    }

    public static Text getInstance(){
        return instance;
    }


    public String format(String message){
        message = message.replace("&a", TextFormat.GREEN + "");
        message = message.replace("&b", TextFormat.AQUA+"");
        message = message.replace("&c", TextFormat.RED+"");
        message = message.replace("&d", TextFormat.LIGHT_PURPLE+"");
        message = message.replace("&e", TextFormat.YELLOW+"");
        message = message.replace("&f", TextFormat.WHITE+"");

        message = message.replace("&0", TextFormat.BLACK+"");
        message = message.replace("&1", TextFormat.DARK_BLUE+"");
        message = message.replace("&2", TextFormat.DARK_GREEN+"");
        message = message.replace("&3", TextFormat.DARK_AQUA+"");
        message = message.replace("&4", TextFormat.DARK_RED+"");
        message = message.replace("&5", TextFormat.DARK_PURPLE+"");
        message = message.replace("&6", TextFormat.GOLD+"");
        message = message.replace("&7", TextFormat.GRAY+"");
        message = message.replace("&8", TextFormat.DARK_GRAY+"");
        message = message.replace("&9", TextFormat.BLUE+"");

        message = message.replace("&l", TextFormat.BOLD+"");
        message = message.replace("&n", TextFormat.UNDERLINE+"");
        message = message.replace("&r", TextFormat.RESET+"");
        message = message.replace("&k", TextFormat.OBFUSCATED+"");

        message = message.replace("{line}", "\n");

        return message;
    }
}