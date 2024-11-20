package dev.piotrulla.simplediscordreward;

import eu.okaeri.configs.OkaeriConfig;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class DiscordRewardInMemoryRepository extends OkaeriConfig implements DiscordRewardRepository {

    public Set<DiscordRewardEntry> entries = new HashSet<>();

    @Override
    public boolean isRewardCollected(long userId) {
        return this.entries.stream().anyMatch(entry -> entry.userId() == userId);
    }

    @Override
    public boolean isRewardCollected(UUID uniqueId) {
        return this.entries.stream().anyMatch(entry -> entry.uniqueId().equals(uniqueId));
    }

    @Override
    public CompletableFuture<Boolean> collect(long userId, UUID uniqueId) {
        if (this.isRewardCollected(userId) || this.isRewardCollected(uniqueId)) {
            return CompletableFuture.completedFuture(false);
        }

        this.entries.add(new DiscordRewardEntry(userId, uniqueId, Instant.now()));
        this.save();

        return CompletableFuture.completedFuture(true);
    }
}
