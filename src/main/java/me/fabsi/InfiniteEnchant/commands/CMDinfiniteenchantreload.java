package me.fabsi.InfiniteEnchant.commands;

import me.fabsi.InfiniteEnchant.config.InfiniteEnchantConfig;
import me.fabsi.InfiniteEnchant.config.InfiniteEnchantConfig.CONFIGTEXT;
import me.fabsi.InfiniteEnchant.utils.MessageSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Objects;

public class CMDinfiniteenchantreload extends BukkitCommand {

	private final MessageSender messageSender;
	private final InfiniteEnchantConfig config;

	public CMDinfiniteenchantreload(MessageSender messageSender, InfiniteEnchantConfig config) {
		super("infiniteenchantreload");
		this.messageSender = messageSender;
		this.config = config;
		this.description = "Reload the configuration file";
		this.usageMessage = "/infiniteenchantreload";
		this.setPermission("infiniteenchant.reload");
		this.setAliases(new ArrayList<>());
	}

	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
		if (!sender.hasPermission(Objects.requireNonNull(this.getPermission()))) {
			return true;
		}
		config.reload();
		messageSender.sendIfNotVoid(sender, CONFIGTEXT.RELOADED_CONFIG);
		return true;
	}
}