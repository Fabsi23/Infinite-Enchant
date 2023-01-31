package me.fabsi.InfiniteEnchant;

import me.fabsi.InfiniteEnchant.commands.CMDinfiniteenchantreload;
import me.fabsi.InfiniteEnchant.utils.MessageSender;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import me.fabsi.InfiniteEnchant.commands.CMDinfiniteenchant;
import me.fabsi.InfiniteEnchant.config.InfiniteEnchantConfig;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class InfiniteEnchant extends JavaPlugin {

	/**
	 * author: Fabsi Date: 31.01.2023 - 31.01.2023 (DMY) Last edited: 31.01.2023
	 */

	private final static Logger LOGGER = Logger.getLogger(InfiniteEnchant.class.getName());

	private InfiniteEnchant plugin;
	private InfiniteEnchantConfig config;

	@Override
	public void onEnable() {
		long current = System.currentTimeMillis();
		plugin = this;
		enableBStats();
		loadConfigurations();
		registerCommands();
		LOGGER.info("Plugin activated! Starting took " + (System.currentTimeMillis() - current) + " ms.");
	}

	@Override
	public void onDisable() {
		LOGGER.info("Plugin deactivated!");
	}

	private void loadConfigurations() {
		saveDefaultConfig();
		config = new InfiniteEnchantConfig(plugin);
		config.reload();
	}

	private void enableBStats() {
		int pluginId = 17601;
		new Metrics(this, pluginId);
	}

	private void registerCommands() {
		MessageSender messageSender = new MessageSender(config);
		try {
			Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			bukkitCommandMap.setAccessible(true);
			CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
			commandMap.register("infiniteenchantreload", new CMDinfiniteenchantreload(messageSender, config));
			commandMap.register("infiniteenchant", new CMDinfiniteenchant(messageSender));
			bukkitCommandMap.setAccessible(false);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			LOGGER.severe("Could not register commands");
			e.printStackTrace();
		}
	}
}