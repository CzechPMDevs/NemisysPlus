package CzechPMDevs;


import com.sun.xml.internal.bind.v2.TODO;
import org.itxtech.nemisys.Player;
import org.itxtech.nemisys.Server;
import org.itxtech.nemisys.event.EventHandler;
import org.itxtech.nemisys.event.Listener;
import org.itxtech.nemisys.event.client.PluginMsgRecvEvent;
import org.itxtech.nemisys.network.protocol.mcpe.TextPacket;
import org.itxtech.nemisys.plugin.PluginBase;
import org.itxtech.nemisys.utils.Config;
import org.itxtech.nemisys.utils.TextFormat;
import CzechPMDevs.Staff.StaffManager;

import CzechPMDevs.Untils.Text;

public class NemisysPlus extends PluginBase implements Listener {

    public Config cfg;

    public StaffManager staffManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.cfg = this.getConfig();

        this.getLogger().info(String.valueOf(this.getDataFolder().mkdirs()));
        this.getServer().getPluginManager().registerEvents(this, this);

        this.safeStart();
    }

    private void safeStart(){
        //More will be here soon

        //Start Text Untils
        new Text();
        this.staffManager = new StaffManager(this);
    }

    //Soon will be in separated in other file
    @EventHandler
    public void onDataReceive(PluginMsgRecvEvent event) {
        String channel = event.getChannel();
        String data = new String(event.getData());

        String[] Data = data.split(":");

        Player player;

        switch (channel) {
            /*case "Game":
                break;*/
            case "MessageAll":
                TextPacket textPacket = new TextPacket();
                textPacket.type = TextPacket.TYPE_RAW;
                textPacket.message = Text.getInstance().format(data);

                Server.broadcastPacket(this.getServer().getOnlinePlayers().values(), textPacket);
                break;
            case "Message":
                player = this.getServer().getPlayer(Data[0]);
                if (player == null) break;
                player.sendMessage(Text.getInstance().format(Data[1]));
                break;

            case "Tip":
                TextPacket tipPacket = new TextPacket();
                tipPacket.type = TextPacket.TYPE_TIP;
                tipPacket.message = Text.getInstance().format(data);

                Server.broadcastPacket(this.getServer().getOnlinePlayers().values(), tipPacket);
                break;
            case "Title":
                TextPacket titlePacket = new TextPacket();
                titlePacket.type = TextPacket.TYPE_WHISPER;
                titlePacket.source = Text.getInstance().format(data);

                Server.broadcastPacket(this.getServer().getOnlinePlayers().values(), titlePacket);
                break;
            case "Kick":
                player = this.getServer().getPlayer(Data[0]);
                String reason = TextFormat.RED + "You was kicked! " + TextFormat.YELLOW + "Join Again";

                if (player == null) break;
                player.close(reason);
                break;

            case "Staff":
                this.getLogger().info(TextFormat.RED + "Staff:" + TextFormat.GOLD + data);

                switch (Data[0]){ //Data[0] = Status_Join|Status_Leave|Message
                    case "Status_Join":
                        player = this.getServer().getPlayer(Data[1]);
                        this.staffManager.staffChat.addChatter(player);
                        break;
                    case  "Status_Leave":
                        player = this.getServer().getPlayer(Data[1]);
                        this.staffManager.staffChat.removeChatter(player);
                        break;
                    case "Message":
                        String sender = Data[1];
                        String message = Data[2];
                        this.staffManager.sendSenderMessage(message, sender);
                        break;
                    case "Player_LOGIN":
                        player = this.getServer().getPlayer(Data[1]);
                        this.staffManager.addStaff(player);
                        this.getLogger().info(TextFormat.RED + "Staff Join:" + TextFormat.GOLD + Data[1]);
                        break;
                    case "Player_LOGOUT":
                        player = this.getServer().getPlayer(Data[1]);
                        this.staffManager.removeStaff(player);
                        break;
                }
                break;

            default:
                break;
        }

        this.getLogger().info(TextFormat.RED + "Packet:" + TextFormat.GOLD + data);
    }

}
