package main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class _setName_ implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        File file = new File(main.getInstance().getDataFolder(),"settings.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (strings.length == 2 && strings[0].equals("set")) {
                if (commandSender instanceof Player) {
                    Player p = (Player) commandSender;
                    if (p.hasPermission("cn.set")) {
                        //    /cn set 带师
                        String seted_string = strings[1].replaceAll("&", "§");
                        if (!commandSender.hasPermission("cn.maxlength.bypass")) {
                            int length = seted_string.length();
                            if (length <= config.getInt("maxlength")) {
                                p.setDisplayName(seted_string + ChatColor.RESET);
                                if (config.getBoolean("tablist") == true) {
                                    p.setPlayerListName(seted_string + ChatColor.RESET);
                                }
                                p.sendMessage("§e§l你的中文名设置好了： " + ChatColor.RESET + seted_string);
                                main.getInstance().getConfig().set(p.getName(), seted_string);
                                main.getInstance().saveConfig();
                            } else {
                                commandSender.sendMessage("§d§l中文名超过最大长度:" + config.getInt("maxlength") + "个字符");
                            }
                        }else{
                            p.setDisplayName(seted_string + ChatColor.RESET);
                            if (config.getBoolean("tablist") == true) {
                                p.setPlayerListName(seted_string + ChatColor.RESET);
                            }
                            p.sendMessage("§e§l你的中文名设置好了： " + ChatColor.RESET + seted_string);
                            main.getInstance().getConfig().set(p.getName(), seted_string);
                            main.getInstance().saveConfig();
                        }
                    } else {
                        commandSender.sendMessage("§4§l你缺少cn.set的权限!");
                    }
                } else {
                    commandSender.sendMessage("§4§l该命令只能玩家使用");
                }
            }else if (strings.length == 3 && strings[0].equals("set") && strings[2].equals("-t")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("cn.set.temporarily")) {
                    //   /cn set 带师 -temporarily
                    String seted_string = strings[1].replaceAll("&", "§");
                    if (!commandSender.hasPermission("cn.maxlength.bypass")) {
                        int length = seted_string.length();
                        if (length <= config.getInt("maxlength")) {
                            p.setDisplayName(seted_string + ChatColor.RESET);
                            if (config.getBoolean("tablist") == true) {
                                p.setPlayerListName(seted_string + ChatColor.RESET);
                            }
                            p.sendMessage("§e§l你的§b§l临时中文名§e§l设置好了： " + ChatColor.RESET + seted_string);
                        } else {
                            commandSender.sendMessage("§d§l中文名超过最大长度:" + config.getInt("maxlength") + "个字符");
                        }
                    } else {
                        p.setDisplayName(seted_string + ChatColor.RESET);
                        if (config.getBoolean("tablist") == true) {
                            p.setPlayerListName(seted_string + ChatColor.RESET);
                        }
                        p.sendMessage("§e§l你的中文名设置好了： " + ChatColor.RESET + seted_string);
                        main.getInstance().getConfig().set(p.getName(), seted_string);
                        main.getInstance().saveConfig();
                    }

                } else {
                    commandSender.sendMessage("§4§l你缺少cn.set.temporarily的权限!");
                }
            } else {
                commandSender.sendMessage("§4§l该命令只能玩家使用");
            }
        }else if (strings.length == 1 && strings[0].equals("set")) {
                //   /cn set
                    commandSender.sendMessage("§b参数不对诶?? 你是不是要用/cn set 名称 [-t] QAQ??");
            }

        if (strings.length == 3 && strings[0].equals("setother")) {
            if (commandSender.hasPermission("cn.setother")) {
                //  /cn setother rina 带师
                String seted_string = strings[2];
                Player seted = Bukkit.getPlayer(strings[1]);
                seted_string = seted_string.replaceAll("&", "§");
                if (!commandSender.hasPermission("cn.maxlength.bypass")) {
                    int length = seted_string.length();
                    if (length <= config.getInt("maxlength")) {
                    commandSender.sendMessage("§e§l" + strings[1] + "§e§l的中文名设置好了： " + ChatColor.RESET + seted_string);
                    seted.setDisplayName(seted_string + ChatColor.RESET);
                    if (config.getBoolean("tablist") == true) {
                        seted.setPlayerListName(seted_string + ChatColor.RESET);
                    }
                    main.getInstance().getConfig().set(seted.getName(), seted_string);
                    main.getInstance().saveConfig();
                }else {
                        commandSender.sendMessage("§d§l中文名超过最大长度:" + config.getInt("maxlength") + "个字符");
                    }
                }else{
                    commandSender.sendMessage("§e§l" + strings[1] + "§e§l的中文名设置好了： " + ChatColor.RESET + seted_string);
                    seted.setDisplayName(seted_string + ChatColor.RESET);
                    if (config.getBoolean("tablist") == true) {
                        seted.setPlayerListName(seted_string + ChatColor.RESET);
                    }
                    main.getInstance().getConfig().set(seted.getName(), seted_string);
                    main.getInstance().saveConfig();
                }
            } else {
                commandSender.sendMessage("§4§l你缺少cn.setother的权限!");
            }
        }else if (strings.length == 4 && strings[0].equals("setother") && strings[3].equals("-t")) {
            if (commandSender.hasPermission("cn.setother.temporarily")) {
                //   /cn setother rina 带师 -t
                String seted_string = strings[2];
                Player seted = Bukkit.getPlayer(strings[1]);
                seted_string = seted_string.replaceAll("&", "§");
                if (!commandSender.hasPermission("cn.maxlength.bypass")) {
                    int length = seted_string.length();
                    if (length <= config.getInt("maxlength")) {
                        commandSender.sendMessage("§e§l" + strings[1] + "§e§l的§b§l临时中文名§e§l设置好了： " + ChatColor.RESET + seted_string);
                        seted.setDisplayName(seted_string + ChatColor.RESET);
                        if (config.getBoolean("tablist") == true) {
                            seted.setPlayerListName(seted_string + ChatColor.RESET);
                        }
                    } else {
                        commandSender.sendMessage("§d§l中文名超过最大长度:" + config.getInt("maxlength") + "个字符");
                    }
                } else {
                    commandSender.sendMessage("§e§l" + strings[1] + "§e§l的中文名设置好了： " + ChatColor.RESET + seted_string);
                    seted.setDisplayName(seted_string + ChatColor.RESET);
                    if (config.getBoolean("tablist") == true) {
                        seted.setPlayerListName(seted_string + ChatColor.RESET);
                    }
                    main.getInstance().getConfig().set(seted.getName(), seted_string);
                    main.getInstance().saveConfig();
                }
            }else {
                commandSender.sendMessage("§4§l你缺少cn.setother.temporarily的权限!");
            }
        }else if (strings.length == 1 && strings[0].equals("setother")) {
            //   /cn setother
            commandSender.sendMessage("§b参数不对诶?? 你是不是要用/cn setother 名称 [-t] QAQ??");
        }

        if (strings.length == 2 && strings[0].equals("reset")) {
            //    /cn reset rina
            if (commandSender.hasPermission("cn.reset")) {
                Player seted = Bukkit.getPlayer(strings[1]);
                seted.setDisplayName(strings[1]);
                seted.sendMessage("§e§l已经强制更改为" + strings[1] + "§e§l的原名");
                if (config.getBoolean("tablist") == true) {
                    seted.setPlayerListName(strings[1] + ChatColor.RESET);
                }
                main.getInstance().getConfig().set(seted.getName(), strings[1]);
                main.getInstance().saveConfig();
            } else {
                commandSender.sendMessage("§4§l你缺少cn.reset的权限!");
            }
        }else if (strings.length == 3 && strings[0].equals("reset") && strings[2].equals("-t")){
            //  /cn reset rina -t
            if (commandSender.hasPermission("cn.reset.temporarily")){
                Player seted = Bukkit.getPlayer(strings[1]);
                seted.setDisplayName(strings[1]);
                if (config.getBoolean("tablist") == true) {
                    seted.setPlayerListName(strings[1] + ChatColor.RESET);
                }
                seted.sendMessage("§e§l已经临时强制更改为" + strings[1] + "§e§l的原名");
            }else {
                commandSender.sendMessage("§4§l你缺少cn.reset.temporarily的权限!");
            }
        }else if (strings.length == 1 && strings[0].equals("reset")) {
            //   /cn reset
            commandSender.sendMessage("§b参数不对诶?? 你是不是要用/cn reset 名称 [-t] QAQ??");
        }

        Plugin plugin = main.getPlugin(main.class);
        if (strings.length == 2 && strings[0].equals("check")){
            //  /cn check rina
            if (commandSender.hasPermission("cn.check")){
                String searched = strings[1];
                String searcher = plugin.getConfig().getString(strings[1]);
                Player p = Bukkit.getPlayer(strings[1]);
                String cn = p.getDisplayName();
                if (searcher != null && !searched.equals(searcher)){
                    commandSender.sendMessage("§b§l玩家 " + strings[1] + " §b§l的中文名是 " + plugin.getConfig().getString(searched));
                }else if (searcher != null && searched.equals(searcher)){
                    commandSender.sendMessage("§b§l玩家 " + strings[1] + " §b§l的中文名已经被重置了!");
                }else if (searcher == null && !cn.equals(searched)){
                    commandSender.sendMessage("§b§l玩家 " + strings[1] + " §b§l的§e§l临时中文名§b§l是 " + cn);
                }else {
                    commandSender.sendMessage("§4§l该玩家暂未拥有中文名");
                }
            }else {
                commandSender.sendMessage("§4§l你缺少cn.check的权限!");
            }
        }else if (strings.length == 1 && strings[0].equals("check")) {
            //   /cn check
            commandSender.sendMessage("§b参数不对诶?? 你是不是要用/cn check 名称 QAQ??");
        }

        ItemStack card = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta = card.getItemMeta();
        String displayName = config.getString("name");
        itemMeta.setDisplayName(config.getString("name"));
        List<String> lores = config.getStringList("lores");
        itemMeta.setLore(lores);
        card.setItemMeta(itemMeta);

        if (strings.length == 2 && strings[0].equals("givecard")) {
            //  /cn givecard rina
            Player p = Bukkit.getPlayer(strings[1]);
                if (commandSender.hasPermission("cn.givecard")) {
                    Inventory inventory = p.getInventory();
                    inventory.addItem(new ItemStack[]{card});
                    commandSender.sendMessage("§b成功给予" + strings[1] +"改名卡*1");
                    p.sendMessage("§b你被给予改名卡*1");
                } else {
                    commandSender.sendMessage("§4§l你缺少cn.givecard的权限");
                }
            }else if (strings.length == 1 && strings[0].equals("givecard")) {
            //   /cn givecard
            commandSender.sendMessage("§b参数不对诶?? 你是不是要用/cn givecard 名称 QAQ??");
        }

            if (strings.length == 2 && strings[0].equals("usecard")) {
                //  /cn usecard abc
                if (commandSender instanceof Player) {
                    Player p = (Player)commandSender;
                    Inventory inv = p.getInventory();
                if (p.hasPermission("cn.usecard")) {
                    for (int packet = 0; packet < inv.getSize(); ++packet) {
                        if (inv.getItem(packet) != null) {
                            ItemStack item = inv.getItem(packet);
                            Boolean material = item.getType().equals(Material.NAME_TAG);
                            if (material) {
                                Boolean loresRight = item.getItemMeta().getLore().equals(lores);
                                Boolean displayNameRight = item.getItemMeta().getDisplayName().equals(displayName);
                                if (loresRight && displayNameRight) {
                                    strings[1] = strings[1].replaceAll("&", "§");
                                    String seted_string = strings[1];
                                    if (!commandSender.hasPermission("cn.maxlength.bypass")) {
                                        int length = seted_string.length();
                                        if (length <= config.getInt("maxlength")) {
                                            p.setDisplayName(strings[1] + ChatColor.RESET);
                                            p.sendMessage("§e§l你的中文名设置好了： " + ChatColor.RESET + strings[1]);
                                            main.getInstance().getConfig().set(p.getName(), strings[1]);
                                            main.getInstance().saveConfig();
                                        }else {
                                                commandSender.sendMessage("§d§l中文名超过最大长度:" + config.getInt("maxlength") + "个字符");
                                            }
                                        }else{
                                            p.setDisplayName(seted_string + ChatColor.RESET);
                                            if (config.getBoolean("tablist") == true) {
                                                p.setPlayerListName(seted_string + ChatColor.RESET);
                                            }
                                            p.sendMessage("§e§l你的中文名设置好了： " + ChatColor.RESET + seted_string);
                                            main.getInstance().getConfig().set(p.getName(), seted_string);
                                            main.getInstance().saveConfig();
                                        }
                                    if (item.getAmount() == 1) {
                                        inv.setItem(packet, new ItemStack(Material.AIR));
                                    } else {
                                        ItemStack cardStack = inv.getItem(packet);
                                        cardStack.setAmount(cardStack.getAmount() - 1);
                                        inv.setItem(packet, cardStack);
                                    }
                                }
                            }
                        }
                    }
                }else {
                    commandSender.sendMessage("§4§l你缺少cn.usecard的权限");
                }
                }else {
                    commandSender.sendMessage("§4§l该命令仅玩家使用");
                }
            }else if (strings.length == 1 && strings[0].equals("usecard")) {
                //   /cn usecard
                commandSender.sendMessage("§b参数不对诶?? 你是不是要用/cn usecard 名称 QAQ??");
            }

            if (
                    strings.length == 1 && !(strings[0].equals("set")
                    || strings[0].equals("reset")
                    || strings[0].equals("setother")
                    || strings[0].equals("check")
                    || strings[0].equals("givecard")
                    || strings[0].equals("usecard")
                    || strings[0].equals("help"))
            ){
                commandSender.sendMessage("§d小雨不能理解你在干啥诶?? 使用/cn help 1/2 ~~~ 查询帮助");
            }

        if (
                (strings.length == 0) || (strings.length == 1
                && strings[0].equals("help"))
                || (strings.length == 2
                && strings[0].equals("help")
                && strings[1].equals("1"))
        ){
            commandSender.sendMessage("§b§l=========第一页=========");
            commandSender.sendMessage("§d/cn help 1/2 ~~~ 查询帮助");
            commandSender.sendMessage("§a/cn set 名称 ~~~ 设置自己的中文名 - cn.set");
            commandSender.sendMessage("§a/cn set 名称 -t ~~~ 设置自己的临时中文名 - cn.set.temporarily");
            commandSender.sendMessage("§a/cn setother ID 名称 ~~~ 设置他人的中文名 - cn.setother");
            commandSender.sendMessage("§a/cn setother ID 名称 -t ~~~ 设置他人的临时中文名 - cn.setother.temporarily");
            commandSender.sendMessage("§a/cn reset ID ~~~ 重置某人的中文名 - cn.reset");
            commandSender.sendMessage("§a/cn reset ID -t ~~~ 临时重置某人的中文名 - cn.reset.temporarily");
            commandSender.sendMessage("§e§l你的支持是小雨最大的动力");
            commandSender.sendMessage("§b§l=========共两页=========");
        }else if (
                strings.length == 2
                        && strings[0].equals("help")
                        && strings[1].equals("2")
        ){
            commandSender.sendMessage("§b§l=========第二页=========");
            commandSender.sendMessage("§d/cn help 1/2 ~~~ 查询帮助");
            commandSender.sendMessage("§a/cn check ID ~~~ 查询某人的中文名 - cn.check");
            commandSender.sendMessage("§a/cn givecard ID ~~~ 给予某人一张改名卡 - cn.getcard");
            commandSender.sendMessage("§a/cn usecard ~~~ 食用一张改名卡 - cn.usecard");
            commandSender.sendMessage("§e§l你的支持是小雨最大的动力");
            commandSender.sendMessage("§b§l=========共两页=========");
        }
        return true;
    }
}
