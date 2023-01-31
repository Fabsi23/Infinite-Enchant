package me.fabsi.InfiniteEnchant.commands;

import me.fabsi.InfiniteEnchant.config.InfiniteEnchantConfig.CONFIGTEXT;
import me.fabsi.InfiniteEnchant.utils.MessageSender;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class CMDinfiniteenchant extends BukkitCommand {

    private final MessageSender messageSender;

    public CMDinfiniteenchant(MessageSender messageSender) {
        super("infiniteenchant");
        this.messageSender = messageSender;
        this.description = "Enchant the item in your main hand";
        this.usageMessage = "/infiniteenchant";
        this.setPermission("infiniteenchant.infiniteenchant");
        this.setAliases(new ArrayList<>());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            System.out.println("This command is only for players.");
            return true;
        }
        if (args.length == 1) {
            enchantItemInHand(player, args[0], 1);
            return true;
        }
        if (args.length == 2) {
            try {
                int level = Integer.parseInt(args[1]);
                enchantItemInHand(player, args[0], level);
            } catch (NumberFormatException e) {
                messageSender.sendIfNotVoid(player, CONFIGTEXT.ITEM_ENCHANT_FAILED_INVALID_LEVEL);
            }
            return true;
        }
        messageSender.sendIfNotVoid(player, CONFIGTEXT.INFINITEENCHANT_HELP);
        return true;
    }

    private void enchantItemInHand(Player player, String enchantmentKey, int level) {
        if (level > 255) {
            level = 255;
        }
        ItemStack inHand = player.getInventory().getItemInMainHand();
        if (inHand.getType() == Material.AIR) {
            messageSender.sendIfNotVoid(player, CONFIGTEXT.ITEM_ENCHANT_FAILED_EMPTY_HAND);
            return;
        }
        Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchantmentKey.toLowerCase()));
        if (enchantment == null) {
            messageSender.sendIfNotVoid(player, CONFIGTEXT.ITEM_ENCHANT_FAILED_INVALID_ENCHANTMENT);
            return;
        }
        inHand.addUnsafeEnchantment(enchantment, level);
        messageSender.sendIfNotVoid(player, CONFIGTEXT.ITEM_ENCHANTED);
    }
}