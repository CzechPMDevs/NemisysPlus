package CzechPMDevs.Untils;

import org.itxtech.nemisys.Client;
import org.itxtech.nemisys.Player;
import org.itxtech.nemisys.synapse.Synapse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStorage {

    private Player player;
    private String name;
    private UUID uuid;

    private Map<String, Object> data = new HashMap<String, Object>();

    public PlayerStorage(Player player, Map<String, Object> data) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUuid();

        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public UUID getID(){
        return this.uuid;
    }

    public Player getPlayer(){
        return this.player;
    }

    /** Returns Synapse Client| May be useful*/
    public Client getSynapse(){
        return this.player.getClient();
    }

    public Map getData(){
        return this.data;
    }

    public void saveToData(String key, Object data){
        this.data.put(key, data);

    }

    public void removeByKey(String key){
        this.data.remove(key);
    }

    public void unset(String key){
        this.removeByKey(key);
    }

    public boolean isset(String key){
        return this.data.containsKey(key) && this.data.get(key) != null;
    }

    public void newData(Map data){
        this.data = data;
    }
}
