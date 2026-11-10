package com.kingsystem11.discordchatbridge;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Listens for player chat and relays it to Discord.
 *
 * @author KingSystem11
 */
public class ChatListener implements Listener {

    private final DiscordChatBridge plugin;
    private final DiscordWebhook webhook;

    public ChatListener(DiscordChatBridge plugin, DiscordWebhook webhook) {
        this.plugin = plugin;
        this.webhook = webhook;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String format = plugin.getConfig().getString("message-format", "{message}");
        String content = format
                .replace("{player}", event.getPlayer().getName())
                .replace("{message}", event.getMessage());

        webhook.send(event.getPlayer().getName(), content);
    }
}
