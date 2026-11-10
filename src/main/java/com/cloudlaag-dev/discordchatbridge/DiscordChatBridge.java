package com.cloudlaag-dev.discordchatbridge;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * DiscordChatBridge
 *
 * Forwards Minecraft server chat messages to a Discord channel
 * through a webhook. Compatible with Spigot and Paper 1.13+.
 *
 * @author KingSystem11
 * @version 1.1.0
 */
public class DiscordChatBridge extends JavaPlugin {

    private DiscordWebhook webhook;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        String url = getConfig().getString("webhook-url", "").trim();
        if (!DiscordWebhook.isValidUrl(url)) {
            getLogger().warning("No valid webhook-url in config.yml. Chat relay is disabled.");
            return;
        }

        webhook = new DiscordWebhook(this, url);
        getServer().getPluginManager().registerEvents(
                new ChatListener(this, webhook), this);

        getLogger().info("DiscordChatBridge enabled. Made by KingSystem11.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DiscordChatBridge disabled.");
    }
}
