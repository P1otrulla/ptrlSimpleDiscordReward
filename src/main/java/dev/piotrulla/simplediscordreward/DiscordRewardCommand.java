package dev.piotrulla.simplediscordreward;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DiscordRewardCommand implements CommandExecutor {

    private final DiscordRewardConfiguration configuration;

    public DiscordRewardCommand(DiscordRewardConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (String line : this.configuration.command) {
            commandSender.sendMessage(translateColor(line));
        }

        return true;
    }

    String translateColor(String color) {
        return ChatColor.translateAlternateColorCodes('&', color);
    }
}
