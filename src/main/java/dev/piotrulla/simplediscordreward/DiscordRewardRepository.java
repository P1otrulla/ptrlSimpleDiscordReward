package dev.piotrulla.simplediscordreward;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface DiscordRewardRepository {

    boolean isRewardCollected(long userId);

    boolean isRewardCollected(UUID uniqueId);

    CompletableFuture<Boolean> collect(long userId, UUID uniqueId);

}
