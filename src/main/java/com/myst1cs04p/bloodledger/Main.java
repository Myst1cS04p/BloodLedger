package com.myst1cs04p.bloodledger;

import org.bukkit.plugin.java.JavaPlugin;
import com.myst1cs04p.bloodledger.listeners.*;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig(); // Initialize config.yml
        registerEvents();
        registerCommands();
        getLogger().info("BloodLedger enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("BloodLedger disabled.");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }

    private void registerCommands() {
        // getCommand("killboard").setExecutor(new KillBoardCommand());
    }

    public static Main getInstance() {
        return instance;
    }
}
