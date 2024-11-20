package dev.piotrulla.simplediscordreward.discord;

import dev.piotrulla.simplediscordreward.DiscordRewardConfiguration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.List;
import java.util.logging.Logger;

public class DiscordMessageService {

    public static final String BUTTON_ID = "open_text_modal";

    private final DiscordRewardConfiguration configuration;
    private final Logger logger;
    private final JDA jda;

    public DiscordMessageService(DiscordRewardConfiguration configuration, Logger logger, JDA jda) {
        this.configuration = configuration;
        this.logger = logger;
        this.jda = jda;
    }

    TextChannel getChannel() {
        TextChannel channelById = this.jda.getChannelById(TextChannel.class, this.configuration.embed.channelId);

        if (channelById == null) {
            this.logger.warning("Discord reward channel not found, using the first channel instead.");
            return this.jda.getTextChannels().get(0);
        }

        return channelById;
    }

    public boolean isMessageCreated() {
        if (this.getChannel() == null) {
            return false;
        }

        try {
            List<Message> messages = this.getChannel().getHistory().retrievePast(1).complete();
            return !messages.isEmpty();
        } catch (Exception ignored) {
            return false;
        }
    }

    public void createMessage() {
        TextChannel channelById = this.getChannel();

        if (channelById == null) {
            return;
        }

        Button button = Button.primary(BUTTON_ID, this.configuration.embed.buttonLabel);

        if (this.configuration.embed.buttonEmoji != null) {
            button = button.withEmoji(Emoji.fromUnicode(this.configuration.embed.buttonEmoji));
        }

        channelById.sendMessageEmbeds(this.configuration.embed.message.toEmbedBuilder())
                .setActionRow(button)
                .queue();
    }
}
