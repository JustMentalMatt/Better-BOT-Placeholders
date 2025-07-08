# Better BungeeOnlineTime Placeholders for PAPI
**Flexible leaderboard placeholders for BungeeOnlineTime. Because the defaults are... not very good?**

> Requires: [BungeeOnlineTime](https://github.com/R3fleXi0n/BungeeOnlineTime) (obviously), [PAPI](https://github.com/PlaceholderAPI/PlaceholderAPI) (for placeholders) and [Snap](https://github.com/Phoenix616/Snap) (for Velocity networks)
#### Oh, also make sure BungeeOnlineTime is using MySQL, because thats literally the only way the plugin talks to it.

>  Supports Velocity* and Bungeecord/Waterfall.

> See [VelocityPlaytime](https://github.com/GRX005/Velocity-PlayTime) for a Velocity specific alternative, because this shit is sure to break in the future :D - but the sole reason THIS PLUGIN EXISTS, is because that one wasnt working for me :)

#### *Bungeecord and Waterfall are basically old, and Velocity is the norm, but you NEED [Snap](https://github.com/Phoenix616/Snap) to use Bungeecord/Waterfall plugins in Velocity. I don't even know why you're here if you don't have Snap already installed, or PLEASE update your servers proxy and use Velocity 😭 Snap will let you use your old plugins.
---

## 🧠 What This Does (And Doesn’t)

BungeeOnlineTime gives you basic playtime tracking across a NETWORK — cool, because basically no other plugins do that on the proxy.
This plugin taps into *that* plugin’s database and adds **actual usable placeholders**, especially for **leaderboards**.  
This is basically a QOL addon.

### What this plugin **does**:
- Reads playtime data from your **existing** BungeeOnlineTime database
- Gives you a bunch of **leaderboard placeholders** (1–10)
- Adds clean, flexible placeholders with formatted outputs

### What this plugin **does NOT** do:
- Track playtime itself  
- Replace BungeeOnlineTime  
- Wash your dishes

---

## ⚙️ Setup (a.k.a. How Not to Screw It Up)

1. Drop this plugin into your **server’s** `plugins` folder (not the proxy!).
2. Edit the `config.yml` to match your BungeeOnlineTime database setup.
3. The table name is hardcoded as `BungeeOnlineTime` — that’s non-negotiable, but also the default.
4. ???
5. Hopefully profit.

---

## 🧪 Testing Status

- ✅ Works on: **1.19.4**, PAPI **2.11.6**
- ❓ Probably works on: **1.16.5+**
- ❌ Tested? **No.**

> I run this using a **Velocity proxy**, with BungeeOnlineTime living in the `plugins/snap/` folder.  
> Will this break? **Probably.**  
> Will I maintain this? **Probably not.**

---

## 📂 File Structure

| Component        | Path                                     |
|------------------|------------------------------------------|
| Velocity Proxy   | `plugins/snap/BungeeOnlineTime.jar`      |
| Server (not proxy!) | `plugins/betterbotplaceholders.jar` |

---

## 🧩 Placeholder Reference

### 🏆 Leaderboard Placeholders (1–10)

| Placeholder                           | Example     | Description                     |
|---------------------------------------|-------------|---------------------------------|
| `%bot_topname_1%`                     | `Player123` | Player’s name at rank #1        |
| `%bot_toptime_totalseconds_1%`       | `93600`     | Total seconds played            |
| `%bot_toptime_totalminutes_1%`       | `1560`      | Total minutes played            |
| `%bot_toptime_totalhours_1%`         | `26`        | Total hours played              |
| `%bot_toptime_totaldays_1%`          | `1`         | Total days played               |
| `%bot_toptime_seconds_1%`            | `30`        | Seconds component (0–59)        |
| `%bot_toptime_minutes_1%`            | `45`        | Minutes component (0–59)        |
| `%bot_toptime_hours_1%`              | `2`         | Hours component (0–23)          |
| `%bot_toptime_days_1%`               | `1`         | Days component (0–6)            |
| `%bot_toptime_formatted_1%`          | `1d 2h 30m` | Smart formatted time            |

#### Obviously for top 1, 2, 3, change the fucking number maybe???? 🤯🤯🤯🤯🤯 It only goes up to 10. If you want more maybe ask nicely and let me pet your dog, or just recompile this. It is the source after all.
---

### 👤 Player-Specific Placeholders

| Placeholder                         | Example     | Description                    |
|-------------------------------------|-------------|--------------------------------|
| `%bot_player_totalseconds%`        | `93600`     | Your total seconds             |
| `%bot_player_totalminutes%`        | `1560`      | Your total minutes             |
| `%bot_player_totalhours%`          | `26`        | Your total hours               |
| `%bot_player_totaldays%`           | `1`         | Your total days                |
| `%bot_player_seconds%`             | `30`        | Your current seconds (0–59)    |
| `%bot_player_minutes%`             | `45`        | Your current minutes (0–59)    |
| `%bot_player_hours%`               | `2`         | Your current hours (0–23)      |
| `%bot_player_days%`                | `1`         | Your current days (0–6)        |
| `%bot_player_formatted%`           | `1d 2h 30m` | Your smart formatted time      |

#### These are the [POV: you are yourself] placeholders. Essentially /onlinetime but as a placeholder... with more flexibility.
---

## 🎨 Formatting Notes

- **Components** show individual parts (e.g., hours = 0–23, not total).
- **Formatted placeholders** hide zeroes smartly. `1d 0h 0m` becomes just `1d`.
- If something’s broken or invalid, you'll get `"0"` or `"N/A"` — because I’m not a magician.

---

## 🤯 Final Thoughts

Use your brain. I gave you the building blocks — mix, match, stack, whatever.  
Everything comes from the database. Nothing is magic.  
No data? No output. That’s life.

---

