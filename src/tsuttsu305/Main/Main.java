package tsuttsu305.Main;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	public static Main plugin;
	//msgはAir右クリック使用時
	//err_msgはブロックを使おうとしたけどできなかった時
	public static String msg, err_msg;
	
	
	Logger logger = Logger.getLogger("Minecraft");

	public void onEnable()
	{
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + "version" + pdfFile.getVersion() + " is Enabled");
		getServer().getPluginManager().registerEvents(new Event(), this);
		
		//config系
		getConfig().options().copyDefaults(true);
		saveConfig();
		msg = getConfig().getString("block_potion_use");
		err_msg = getConfig().getString("block_use_error");
		
		
		
	}

	public void onDisable()
	{
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + "version" + pdfFile.getVersion() + " is Disabled");
	}

	

	
}