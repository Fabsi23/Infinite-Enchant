package me.fabsi.InfiniteEnchant.config;

import me.fabsi.InfiniteEnchant.InfiniteEnchant;
import org.bukkit.configuration.file.FileConfiguration;

public class InfiniteEnchantConfig {

    private static final String CONFIG = "Config.";
    private static final String PREFIX = CONFIG + "Prefix.";

    public enum CONFIGTEXT {
        ITEM_ENCHANTED("item-enchanted"),
        ITEM_ENCHANT_FAILED_EMPTY_HAND("item-enchant-failed-empty-hand"),
        ITEM_ENCHANT_FAILED_INVALID_ENCHANTMENT("item-enchant-failed-invalid-enchantment"),
        ITEM_ENCHANT_FAILED_INVALID_LEVEL("item-enchant-failed-invalid-level"),
        INFINITEENCHANT_HELP("infiniteenchant-help"),
        RELOADED_CONFIG("reloaded-config");


        private final String key;

        CONFIGTEXT(String key) {
            this.key = CONFIG + "Messages." + key;
        }
    }

    private final InfiniteEnchant plugin;
    private FileConfiguration cfg;

    public InfiniteEnchantConfig(InfiniteEnchant plugin) {
        this.plugin = plugin;
        cfg = plugin.getConfig();
    }

    public String getMessage(CONFIGTEXT textkey) {
        return cfg.getString(textkey.key);
    }

    public String getPluginPrefix() {
        return cfg.getString(PREFIX + "plugin-prefix");
    }

    public void reload() {
        plugin.reloadConfig();
        cfg = plugin.getConfig();
    }
}
