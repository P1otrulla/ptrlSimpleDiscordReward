package dev.piotrulla.simplediscordreward.discord;

import dev.piotrulla.simplediscordreward.DiscordRewardConfiguration;
import dev.piotrulla.simplediscordreward.DiscordRewardRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class DiscordApplication {

    private final DiscordRewardConfiguration configuration;
    private final DiscordRewardRepository repository;
    private final Server server;
    private final Plugin plugin;

    private JDA jda;

    public DiscordApplication(DiscordRewardConfiguration configuration, DiscordRewardRepository repository, Server server, Plugin plugin) {
        this.configuration = configuration;
        this.repository = repository;
        this.server = server;
        this.plugin = plugin;
    }

    public CompletableFuture<Boolean> enable() {
        this.jda = JDABuilder.createDefault(this.configuration.token, Arrays.asList(GatewayIntent.values()))
                .build();

        if (this.jda == null) {
            return CompletableFuture.completedFuture(false);
        }

        try {
            this.jda.awaitReady();
        }
        catch (InterruptedException ignored) {
            return CompletableFuture.completedFuture(false);
        }

        this.jda.addEventListener(new DiscordController(this.configuration, this.repository, this.server, this.plugin));

        DiscordMessageService messageService = new DiscordMessageService(this.configuration, this.server.getLogger(), this.jda);

        if (!messageService.isMessageCreated()) {
            messageService.createMessage();
        }

        return CompletableFuture.completedFuture(true);
    }

    public void disable() {
        if (this.jda == null) {
            return;
        }

        this.jda.shutdown();
        this.jda = null;
    }
}
