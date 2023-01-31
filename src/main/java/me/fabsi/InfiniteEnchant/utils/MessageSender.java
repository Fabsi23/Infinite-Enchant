package me.fabsi.InfiniteEnchant.utils;

import me.fabsi.InfiniteEnchant.config.InfiniteEnchantConfig;
import me.fabsi.InfiniteEnchant.config.InfiniteEnchantConfig.CONFIGTEXT;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageSender {

	private final InfiniteEnchantConfig config;

	public MessageSender(InfiniteEnchantConfig config) {
		this.config = config;
	}

	public void sendIfNotVoid(CommandSender sender, CONFIGTEXT messageKey) {
		String message = config.getMessage(messageKey);
		if (!(message.trim().equals(""))) {
			message = message.replace("%PREFIX%", config.getPluginPrefix());
			sender.sendMessage(translateColors(message));
		}
	}

	private String translateColors(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
