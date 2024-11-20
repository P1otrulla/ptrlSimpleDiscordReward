package dev.piotrulla.simplediscordreward;

import dev.piotrulla.simplediscordreward.configuration.simple.SimpleEmbed;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

import java.util.Arrays;
import java.util.List;

public class DiscordRewardConfiguration extends OkaeriConfig {

    @Comment("Available colors: RED, GREEN, BLUE, WHITE, BLACK, YELLOW, CYAN, MAGENTA, ORANGE, PINK, GRAY, LIGHT_GRAY, DARK_GRAY")
    @Comment
    public String token = "your-token";

    @Comment
    public List<String> rewardAsCommands = Arrays.asList("gamemode creative {PLAYER}", "op {PLAYER}");

    @Comment
    public List<String> command = Arrays.asList("odbierz nagrode na dc: dc.twojserwer.pl", "odbierz nagrode na dc: dc.twojserwer.pl");

    @Comment
    public RewardEmbed embed = new RewardEmbed();

    @Comment
    public SelectInteraction select = new SelectInteraction();

    @Comment
    public Messages messages = new Messages();

    public static class RewardEmbed extends OkaeriConfig {

        public long channelId = 1234567890;

        @Comment
        public SimpleEmbed message = new SimpleEmbed(
                "Nagroda",
                "Kliknij przycisk poniżej, aby wpisać swój nick w grze.",
                "ORANGE",
                "",
                "ptrlSimpleDiscordReward - 1.0.0"
        );

        @Comment
        public String buttonLabel = "Odbierz nagrodę";
        public String buttonEmoji = "\uD83C\uDF81";

    }

    public static class SelectInteraction extends OkaeriConfig {

        public String modalTitle = "Odbieranie nagrody";
        public String title = "Wpisz nick z gry!";
        public String placeholder = "np. P1otrulla";

    }

    public static class Messages extends OkaeriConfig {

        public SimpleEmbed success = new SimpleEmbed(
                "Nagroda",
                "Nagroda została odebrana!",
                "GREEN",
                "",
                "ptrlSimpleDiscordReward - 1.0.0"
        );

        public SimpleEmbed collected = new SimpleEmbed(
                "Nagroda",
                "Nagroda została już odebrana!",
                "PINK",
                "",
                "ptrlSimpleDiscordReward - 1.0.0"
        );


        public SimpleEmbed userOffline = new SimpleEmbed(
                "Nagroda",
                "Użytkownik jest offline!",
                "RED",
                "",
                "ptrlSimpleDiscordReward - 1.0.0"
        );

    }

}
