package jp.karpen.lFishing.minigame;

import jp.karpen.lFishing.LFishing;
import jp.karpen.lFishing.boxes.*;
import jp.karpen.lFishing.utils.SkinManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FishingMinigame implements Listener {
    private final LFishing plugin;
    private final SkinManager skinManager;
    private final Map<UUID, GameSession> activeGames = new HashMap<>();

    public FishingMinigame(LFishing plugin, SkinManager skinManager) {
        this.plugin = plugin;
        this.skinManager = skinManager;
        plugin.registerListener(this);
    }

    public void start(Player player, Runnable onWin, Runnable onLose) {
        UUID uuid = player.getUniqueId();
        if (activeGames.containsKey(uuid)) return;

        GameSession session = new GameSession(player, onWin, onLose, (p) -> activeGames.remove(p.getUniqueId()));
        activeGames.put(uuid, session);
        session.runTaskTimer(plugin, 0L, 2L);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (activeGames.containsKey(player.getUniqueId())) {
            GameSession session = activeGames.get(player.getUniqueId());

            if (event.getTo() == null) return;
            Vector velocity = event.getTo().toVector().subtract(event.getFrom().toVector());

            Vector direction = player.getLocation().getDirection();
            Vector right = new Vector(-direction.getZ(), 0, direction.getX()).normalize();
            
            double dot = velocity.dot(right);
            
            if (dot > 0.005) {
                session.moveSlider(0.06);
            } else if (dot < -0.005) {
                session.moveSlider(-0.06);
            }

            if (event.getFrom().getX() != event.getTo().getX() || 
                event.getFrom().getY() != event.getTo().getY() || 
                event.getFrom().getZ() != event.getTo().getZ()) {
                event.setTo(event.getFrom().setDirection(event.getTo().getDirection()));
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        GameSession session = activeGames.remove(event.getPlayer().getUniqueId());
        if (session != null) {
            session.cancel();
        }
    }
}
