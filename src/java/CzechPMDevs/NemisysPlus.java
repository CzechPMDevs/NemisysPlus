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

import CzechPMDevs.Untils.Text;

public class NemisysPlus extends PluginBase implements Listener {

    public Config cfg;


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
    }

    //Soon will be in separated in other file
    @EventHandler
    public void onDataReceive(PluginMsgRecvEvent event) {
        String channel = event.getChannel();
        String data = new String(event.getData());

        String[] Data = data.split(":");

        switch (channel) {
            /*case "Game":
                break;*/
            case "MessageAll":
                TextPacket textPacket = new TextPacket();
                textPacket.type = TextPacket.TYPE_RAW;
                textPacket.message = Text.getInstance().format(data);

                Server.broadcastPacket(this.getServer().getOnlinePlayers().values(), textPacket);
                break;
            case "Message": {
                Player player = this.getServer().getPlayer(Data[0]);

                if (player == null) break;
                player.sendMessage(Text.getInstance().format(Data[1]));
                break;
            }
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
            case "Kick": {
                Player player = this.getServer().getPlayer(Data[0]);
                String reason = TextFormat.RED + "You was kicked! " + TextFormat.YELLOW + "Join Again";

                if (player == null) break;
                player.close(reason);
                break;
            }

            case "Staff":{ //TODO: Will redirect to new class
                switch (Data[0]){ //Data[0] = Status_Join|Status_Leave|Message
                    case "Status_Join":
                        //TODO: Implement StaffChat Join
                        break;
                    case  "Status_Leave":
                        //TODO: Implement StaffChat Leave
                        break;
                    case "Message":
                        String sender = Data[1];
                        String message = Data[2];
                        //TODO: Implement Send Message
                        break;
                }
                break;
            }
            default:
                break;
        }

    }
}
