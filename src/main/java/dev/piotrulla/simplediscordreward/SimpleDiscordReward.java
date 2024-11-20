package dev.piotrulla.simplediscordreward;

import dev.piotrulla.simplediscordreward.configuration.ConfigService;
import dev.piotrulla.simplediscordreward.discord.DiscordApplication;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class SimpleDiscordReward extends JavaPlugin {

    private DiscordApplication discordApplication;

    @Override
    public void onEnable() {
        ConfigService configService = new ConfigService();

        DiscordRewardConfiguration configuration = configService.create(DiscordRewardConfiguration.class, new File(this.getDataFolder(), "config.yml"));
        DiscordRewardInMemoryRepository repository = configService.create(DiscordRewardInMemoryRepository.class, new File(this.getDataFolder(), "data/rewards.dat"));

        Logger logger = this.getLogger();

        this.discordApplication = new DiscordApplication(configuration, repository, this.getServer(), this);

        this.discordApplication.enable().whenComplete((result, throwable) -> {
            if (throwable != null) {
                logger.severe("Failed to enable Discord application: " + throwable.getMessage());
                return;
            }

            if (result) {
                logger.info("Discord application enabled");

                this.getCommand("nagroda").setExecutor(new DiscordRewardCommand(configuration));
            }
            else {
                logger.severe("Failed to enable Discord application");
            }
        });
    }

    @Override
    public void onDisable() {
        if (this.discordApplication == null) {
            return;
        }

        this.discordApplication.disable();
    }

}
