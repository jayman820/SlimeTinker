package io.github.sefiraat.slimetinker.items.templates;

import io.github.sefiraat.slimetinker.items.tinkermaterials.TinkerMaterialManager;
import io.github.sefiraat.slimetinker.utils.Experience;
import io.github.sefiraat.slimetinker.utils.Ids;
import io.github.sefiraat.slimetinker.utils.ItemUtils;
import io.github.sefiraat.slimetinker.utils.Keys;
import io.github.sefiraat.slimetinker.utils.ThemeUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.MessageFormat;

public class RodTemplate extends SlimefunItem implements NotPlaceable {

    public RodTemplate(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public static String getName(RodDefinition rodDefinition) {
        return MessageFormat.format(
            "{0}{1}-{2}{3}-{4}{5} {6}{7}",
            TinkerMaterialManager.getById(rodDefinition.getBaseMaterial()).getColor(),
            ThemeUtils.toTitleCase(rodDefinition.getBaseMaterial()),
            TinkerMaterialManager.getById(rodDefinition.getTrimMaterial()).getColor(),
            ThemeUtils.toTitleCase(rodDefinition.getTrimMaterial()),
            TinkerMaterialManager.getById(rodDefinition.getLineMaterial()).getColor(),
            ThemeUtils.toTitleCase(rodDefinition.getLineMaterial()),
            ChatColor.WHITE,
            ThemeUtils.toTitleCase(rodDefinition.getPartType())
        );
    }

    public ItemStack getStack(RodDefinition rodDefinition) {

        ItemStack itemStack = this.getItem().clone();

        itemStack.setType(Material.FISHING_ROD);
        ItemMeta im = itemStack.getItemMeta();
        Experience.setupExpNew(im);
        PersistentDataAPI.setString(im, Keys.TOOL_INFO_IS_TOOL, "Y");
        PersistentDataAPI.setString(im, Keys.TOOL_INFO_HEAD_TYPE, rodDefinition.getClassType());
        PersistentDataAPI.setString(im, Keys.TOOL_INFO_TOOL_TYPE, rodDefinition.getPartType());
        PersistentDataAPI.setString(im, Keys.ROD_INFO_BASE_MATERIAL, rodDefinition.getBaseMaterial());
        PersistentDataAPI.setString(im, Keys.ROD_INFO_TRIM_MATERIAL, rodDefinition.getTrimMaterial());
        PersistentDataAPI.setString(im, Keys.ROD_INFO_LINE_MATERIAL, rodDefinition.getLineMaterial());
        im.setDisplayName(getName(rodDefinition));
        itemStack.setItemMeta(im);
        ItemUtils.rebuildTinkerLore(itemStack);
        return itemStack;
    }

}

