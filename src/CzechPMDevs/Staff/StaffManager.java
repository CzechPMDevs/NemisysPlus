package CzechPMDevs.Staff;

import org.itxtech.nemisys.Nemisys;
import CzechPMDevs.NemisysPlus;
import CzechPMDevs.Untils.Text;
import CzechPMDevs.Staff.StaffChat;
import org.itxtech.nemisys.Player;
import org.itxtech.nemisys.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffManager{

    private static StaffManager instance;

    public NemisysPlus plugin;
    public StaffChat staffChat;

    public Map<String, Player> staff = new HashMap<String, Player>();

    public StaffManager(NemisysPlus nemisysPlus){
        this.plugin = nemisysPlus;
        instance = this;

        this.staffChat = new StaffChat(this);
        this.plugin.getServer().getPluginManager().registerEvents(this.staffChat, this.plugin);
    }

    public static StaffManager getInstance(){
        return instance;
    }

    /** Adding Player to staffGroup*/
    public void addStaff(Player staff){
        this.staff.put(staff.getName(), staff);

        this.staff.forEach((name, player) -> {
            String msg = Text.getInstance().format("&7[&6Staff&7] " + staff.getName() + " connected!");
            player.sendMessage(msg);
        });
    }

    /** Removing Player from staffGroup*/
    public void removeStaff(Player staff){
        this.staff.remove(staff.getName());

        this.staff.forEach((name, player) -> {
            String msg = Text.getInstance().format("&7[&6Staff&7] " + staff.getName() + " disconnected!");
            player.sendMessage(msg);
        });
    }

    public void sendMessage(String message){
        this.staff.forEach((name, player) -> {
            String msg = Text.getInstance().format("&7[&6Staff&7] " + message);
            player.sendMessage(msg);
        });
    }

    public void sendSenderMessage(String message, String sender){
        this.staff.forEach((name, player) -> {
            String msg = Text.getInstance().format("&7[&6Staff " + sender + "&7] " + message);
            player.sendMessage(msg);
        });
    }
}
