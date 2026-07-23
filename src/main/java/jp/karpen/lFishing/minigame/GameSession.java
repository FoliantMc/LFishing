package jp.karpen.lFishing.minigame;

import jp.karpen.lFishing.LFishing;
import jp.karpen.lFishing.utils.LangManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public class GameSession extends BukkitRunnable {
    private final Player player;
    private final Runnable onWin;
    private final Runnable onLose;
    private final Consumer<Player> onComplete;
    private final LangManager lang;
    
    private double sliderPos = 0.5;
    private double targetPos;
    private final double targetWidth = 0.15;
    private double progress = 0.0;
    private int ticks = 0;
    private double targetVelocity = 0.0;

    public GameSession(Player player, Runnable onWin, Runnable onLose, Consumer<Player> onComplete) {
        this.player = player;
        this.onWin = onWin;
        this.onLose = onLose;
        this.onComplete = onComplete;
        this.targetPos = Math.random() * (1.0 - targetWidth);
        this.lang = LFishing.getLangManager();
    }

    public void moveSlider(double delta) {
        sliderPos = Math.clamp(sliderPos + delta, 0.0, 1.0);
    }

    @Override
    public void run() {
        ticks++;

        if (ticks % 10 == 0) {
            targetVelocity = (Math.random() - 0.5) * 0.1; 
        }

        targetPos = Math.clamp(targetPos + targetVelocity, 0.0, 1.0 - targetWidth);

        if (sliderPos >= targetPos && sliderPos <= targetPos + targetWidth) {
            progress += 0.04;
            if (ticks % 5 == 0) player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 2.0f);
        } else {
            double penalty = 0.02 + (ticks / 1000.0); 
            progress = Math.max(0, progress - penalty);
        }

        displayActionBar();

        if (progress >= 1.0) {
            win();
            cancel();
        } else if (ticks > 300) {
            lose();
            cancel();
        }
    }

    private void displayActionBar() {
        int barSize = 20;
        StringBuilder bar = new StringBuilder(ChatColor.GRAY + "[");
        
        for (int i = 0; i < barSize; i++) {
            double currentPos = (double) i / barSize;
            if (currentPos >= targetPos && currentPos <= targetPos + targetWidth) {
                if (Math.abs(currentPos - sliderPos) < 0.05) {
                    bar.append(ChatColor.GREEN + "X");
                } else {
                    bar.append(ChatColor.YELLOW + "=");
                }
            } else if (Math.abs(currentPos - sliderPos) < 0.05) {
                bar.append(ChatColor.RED + "X");
            } else {
                bar.append(ChatColor.WHITE + "-");
            }
        }
        bar.append(ChatColor.GRAY + "] ");
        bar.append(ChatColor.AQUA + "" + (int)(progress * 100) + "%");
        
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(bar.toString()));
    }

    private void win() {
        onComplete.accept(player);
        if (onWin != null) onWin.run();
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + lang.getMessage("msg.caught")));
    }

    private void lose() {
        onComplete.accept(player);
        if (onLose != null) onLose.run();
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + lang.getMessage("msg.un-caught")));
    }
}
