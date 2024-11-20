package dev.piotrulla.simplediscordreward;

import eu.okaeri.configs.OkaeriConfig;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class DiscordRewardEntry extends OkaeriConfig {

    private long userId;
    private UUID uniqueId;
    private Instant issuedAt;

    public DiscordRewardEntry(long userId, UUID uniqueId, Instant issuedAt) {
        this.userId = userId;
        this.uniqueId = uniqueId;
        this.issuedAt = issuedAt;
    }

    public long userId() {
        return this.userId;
    }

    public UUID uniqueId() {
        return this.uniqueId;
    }

    public Instant issuedAt() {
        return this.issuedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiscordRewardEntry that = (DiscordRewardEntry) o;

        return this.userId == that.userId
                && Objects.equals(this.uniqueId, that.uniqueId)
                && Objects.equals(this.issuedAt, that.issuedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId, this.uniqueId, this.issuedAt);
    }

    @Override
    public String toString() {
        return "DiscordRewardEntry{" +
                "userId=" + this.userId +
                ", uniqueId=" + this.uniqueId +
                ", issuedAt=" + this.issuedAt +
                '}';
    }
}
