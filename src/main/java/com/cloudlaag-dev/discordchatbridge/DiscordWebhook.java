package com.cloudlaag-dev.discordchatbridge;

import org.bukkit.Bukkit;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Sends messages to a Discord webhook asynchronously.
 *
 * @author KingSystem11
 */
public class DiscordWebhook {

    private final DiscordChatBridge plugin;
    private final String url;

    public DiscordWebhook(DiscordChatBridge plugin, String url) {
        this.plugin = plugin;
        this.url = url;
    }

    public static boolean isValidUrl(String url) {
        return url != null
                && (url.startsWith("https://discord.com/api/webhooks/")
                || url.startsWith("https://discordapp.com/api/webhooks/"));
    }

    public void send(String username, String content) {
        String payload = "{"
                + "\"username\":\"" + escape(username) + "\","
                + "\"content\":\"" + escape(content) + "\","
                + "\"allowed_mentions\":{\"parse\":[]}"
                + "}";

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> post(payload));
    }

    private void post(String json) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int code = connection.getResponseCode();
            if (code >= 400) {
                plugin.getLogger().warning("Discord webhook returned HTTP " + code);
            }
        } catch (Exception ex) {
            plugin.getLogger().warning("Failed to send message to Discord: " + ex.getMessage());
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    private static String escape(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (char ch : text.toCharArray()) {
            switch (ch) {
                case '"':  sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\n': sb.append("\\n");  break;
                case '\r': sb.append("\\r");  break;
                case '\t': sb.append("\\t");  break;
                default:
                    if (ch < 0x20) {
                        sb.append(String.format("\\u%04x", (int) ch));
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }
}
