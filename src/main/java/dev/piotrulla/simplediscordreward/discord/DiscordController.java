package dev.piotrulla.simplediscordreward.discord;

import dev.piotrulla.simplediscordreward.DiscordRewardConfiguration;
import dev.piotrulla.simplediscordreward.DiscordRewardRepository;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DiscordController extends ListenerAdapter {

    private static final String MODAL_ID = "minecraft_modal";
    private static final String SELECT_ID = "minecraft_nick";

    private final DiscordRewardConfiguration configuration;
    private final DiscordRewardRepository repository;
    private final Server server;
    private final Plugin plugin;

    public DiscordController(DiscordRewardConfiguration configuration, DiscordRewardRepository repository, Server server, Plugin plugin) {
        this.configuration = configuration;
        this.repository = repository;
        this.server = server;
        this.plugin = plugin;
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals(DiscordMessageService.BUTTON_ID)) {
            if (this.repository.isRewardCollected(event.getUser().getIdLong())) {
                event.replyEmbeds(this.configuration.messages.collected.toEmbedBuilder())
                        .setEphemeral(true)
                        .queue();
                return;
            }

            TextInput input = TextInput.create(SELECT_ID, this.configuration.select.title, TextInputStyle.SHORT)
                    .setPlaceholder(this.configuration.select.placeholder)
                    .setMinLength(3)
                    .setMaxLength(36)
                    .setRequired(true)
                    .build();

            Modal modal = Modal.create(MODAL_ID, this.configuration.select.modalTitle)
                    .addActionRow(input)
                    .build();

            event.replyModal(modal).queue();
        }
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals(MODAL_ID)) {
            ModalMapping textInput = event.getValue(SELECT_ID);

            if (textInput != null) {
                String minecraftNick = textInput.getAsString();
                long discordUserId = event.getUser().getIdLong();

                if (this.repository.isRewardCollected(discordUserId)) {
                    event.replyEmbeds(this.configuration.messages.collected.toEmbedBuilder())
                            .setEphemeral(true)
                            .queue();
                    return;
                }

                Player player = this.server.getPlayer(minecraftNick);

                if (player == null) {
                    event.replyEmbeds(this.configuration.messages.userOffline.toEmbedBuilder())
                            .setEphemeral(true)
                            .queue();
                    return;
                }

                if (this.repository.isRewardCollected(player.getUniqueId())) {
                    event.replyEmbeds(this.configuration.messages.collected.toEmbedBuilder())
                            .setEphemeral(true)
                            .queue();
                    return;
                }

                this.repository.collect(discordUserId, player.getUniqueId());

                this.server.getScheduler().runTask(this.plugin, () -> {
                    for (String command : this.configuration.rewardAsCommands) {
                        this.server.dispatchCommand(this.server.getConsoleSender(), command.replace("{PLAYER}", minecraftNick));
                    }
                });

                event.replyEmbeds(this.configuration.messages.success.toEmbedBuilder())
                        .setEphemeral(true)
                        .queue();
            }
        }
    }

}
