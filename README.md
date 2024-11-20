
# ptrlSimpleDiscordReward

`ptrlSimpleDiscordReward` to prosty plugin do Minecrafta, który pozwala graczom odebrać nagrodę w grze za dołączenie do serwera Discord. Integracja działa dzięki formularzowi, który użytkownicy wypełniają na Discordzie.

## Funkcje

- **Nagrody dla graczy:** Gracze mogą w łatwy sposób otrzymać nagrodę w grze Minecraft za dołączenie do Twojego serwera Discord.
- **Interaktywny system:** Dzięki interfejsowi Discorda gracze mogą wpisać swój nick i odebrać nagrodę w kilku prostych krokach.

## Jak to działa?

1. Gracz klika przycisk "Odbierz nagrodę" na Discordzie.
2. W formularzu podaje swój nick z gry Minecraft.
3. Plugin weryfikuje, czy gracz jest online i wydaje nagrodę.

## Zrzuty ekranu

1. **Kanał z nagrodą:**

   ![1](https://github.com/user-attachments/assets/e455a462-01c0-4a5f-80f1-060c6cfdc289)

2. **Formularz zgłoszeniowy:**

   ![2](https://github.com/user-attachments/assets/8667895f-1eb7-475b-b17d-c1fd74de70d6)

3. **Proces odbierania nagrody:**

   ![3](https://github.com/user-attachments/assets/7a4b8858-01a5-4091-9371-d39c62b95925)

## Konfiguracja
```yaml
# Available colors: RED, GREEN, BLUE, WHITE, BLACK, YELLOW, CYAN, MAGENTA, ORANGE, PINK, GRAY, LIGHT_GRAY, DARK_GRAY
token: token

rewardAsCommands:
- "gamemode creative {PLAYER}"
- "op {PLAYER}"

command:
- "odbierz nagrode na dc: dc.twojserwer.pl"
- "odbierz nagrode na dc: dc.twojserwer.pl"

embed:
  channelId: 1308777564336160798

  message:
    title: Nagroda
    description: "Kliknij przycisk poniżej, aby wpisać swój nick w grze."
    color: ORANGE
    author: ''
    footer: "ptrlSimpleDiscordReward - 1.0.0"

  buttonLabel: "Odbierz nagrodę"
  buttonEmoji: 🎁

select:
  modalTitle: "Odbieranie nagrody"
  title: "Wpisz nick z gry!"
  placeholder: "np. P1otrulla"

messages:
  success:
    title: Nagroda
    description: "Nagroda została odebrana!"
    color: GREEN
    author: ''
    footer: "ptrlSimpleDiscordReward - 1.0.0"
  collected:
    title: Nagroda
    description: "Nagroda została już odebrana!"
    color: PINK
    author: ''
    footer: "ptrlSimpleDiscordReward - 1.0.0"
  userOffline:
    title: Nagroda
    description: "Użytkownik jest offline!"
    color: RED
    author: ''
    footer: "ptrlSimpleDiscordReward - 1.0.0"
```
