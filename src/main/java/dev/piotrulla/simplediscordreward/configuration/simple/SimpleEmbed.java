package dev.piotrulla.simplediscordreward.configuration.simple;

import dev.piotrulla.simplediscordreward.shared.ColorUtil;
import eu.okaeri.configs.OkaeriConfig;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class SimpleEmbed extends OkaeriConfig {

    private String title;
    private String description;
    private String color;
    private String author;
    private String footer;

    public SimpleEmbed(String title, String description, String color, String author, String footer) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.author = author;
        this.footer = footer;
    }

    public MessageEmbed toEmbedBuilder() {
        return new EmbedBuilder()
                .setTitle(this.title)
                .setDescription(this.description)
                .setColor(ColorUtil.getColor(this.color))
                .setAuthor(this.author)
                .setFooter(this.footer)
                .build();
    }
}
