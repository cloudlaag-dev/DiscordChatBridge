# DiscordChatBridge

[

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

](LICENSE) 

![Minecraft](https://img.shields.io/badge/Minecraft-1.13%2B-62B47A)



Forward your Minecraft server chat to a Discord channel in real time. Staff and community members can follow in-game conversations directly from Discord, without joining the server.

## Features

- Real-time chat forwarding through a Discord webhook
- Customizable message format with `{player}` and `{message}` placeholders
- Asynchronous HTTP requests, so the main server thread is never blocked
- Mentions are disabled by default, so players cannot ping `@everyone` or roles
- Works on Spigot and Paper 1.13 and newer

## Installation

1. Download `DiscordChatBridge.jar` from the Releases page, or build it yourself.
2. Place the jar in your server's `plugins/` folder.
3. Start the server once so the config file is generated.
4. Create a webhook in your Discord channel: **Channel Settings > Integrations > Webhooks > New Webhook**, then copy the URL.
5. Paste the URL into `plugins/DiscordChatBridge/config.yml`:

```yaml
webhook-url: "https://discord.com/api/webhooks/XXXX/YYYY"

# Message format. Placeholders: {player}, {message}
message-format: "{message}"

# Print extra information to the console
debug: false
Building from source
git clone https://github.com/KingSystem11/DiscordChatBridge.git
cd DiscordChatBridge
mvn -B package
The compiled jar will be in target/DiscordChatBridge.jar.
Security
Your webhook URL is secret. Anyone who has it can post messages to your Discord channel. Never commit it to a public repository.
Support
Bugs and feature requests: open an issue on the Issues page.
Discord: cloudlaag-dev
License
This project is licensed under the MIT License. See the LICENSE file for details.
Made by KingSystem11
