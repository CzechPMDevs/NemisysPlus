package CzechPMDevs.Staff;

import CzechPMDevs.Staff.StaffManager;
import CzechPMDevs.Untils.PlayerStorage;
import CzechPMDevs.Untils.Text;
import org.itxtech.nemisys.Player;
import org.itxtech.nemisys.event.EventHandler;
import org.itxtech.nemisys.event.Listener;
import org.itxtech.nemisys.event.player.PlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class StaffChat implements Listener {

    private StaffManager manager;

    public Map<String, Player> chatters = new HashMap<String, Player>();

    public StaffChat(StaffManager manager){
        this.manager = manager;
    }

    /** Adding Player To CHat Group*/
    public void addChatter(Player player){
        if (this.manager.staff.containsKey(player.getName()) && this.manager.staff.get(player.getName()) != null){
            this.chatters.put(player.getName(), player);
        }
    }

    /** Removing Player from Group*/
    public void removeChatter(Player player){
        this.chatters.remove(player.getName());
    }

    @EventHandler
    public void onChat(PlayerChatEvent event){
        Player player = event.getPlayer();
        if (this.chatters.containsKey(player.getName()) && this.chatters.get(player.getName()) != null){
            String message = event.getMessage();
            event.setCancelled();

            this.chatters.forEach((name, member) -> {
                member.sendMessage(Text.getInstance().format("&7[&6"+player.getName()+"&7]&c " +message));
            });
        }
    }
}
