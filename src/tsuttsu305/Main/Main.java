package tsuttsu305.Main;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public static Main plugin;
    //msgはAir右クリック使用時
    //err_msgはブロックを使おうとしたけどできなかった時
    public static String msg;

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new Event(), this);
		
        //config系
        getConfig().options().copyDefaults(true);
        saveConfig();
        msg = getConfig().getString("block_potion_use");
    }
}
