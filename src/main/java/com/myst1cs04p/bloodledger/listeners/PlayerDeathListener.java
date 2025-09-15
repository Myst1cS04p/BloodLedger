package com.myst1cs04p.bloodledger.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.myst1cs04p.bloodledger.Main;

import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deceased = event.getEntity();
        Player killer = deceased.getKiller(); // null if no killer

        // TODO: Save data (deaths, kills, cause, inventories)
        // Example: StatsManager.recordDeath(deceased, killer, event.getDeathMessage());

        if (killer != null) {
            try {
                Main.getInstance().getLogger().info("[BLOODLEDGER] Logging kill data");

                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = dateTime.format(dateTimeFormatter);

                FileWriter fileWriter = new FileWriter("plugins/BloodLedger/kill_log.txt", true);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                writer.write("[" + formattedDateTime + "] " + killer.getName() + " killed " + deceased.getName());
                writer.newLine();
                writer.close();
            } 
            catch (Exception e) {
                Main.getInstance().getLogger().warning("[BLOODLEDGER] " + e);
            }
        }
    }
}
