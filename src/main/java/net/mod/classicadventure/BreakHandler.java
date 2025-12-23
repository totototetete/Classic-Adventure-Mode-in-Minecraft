package net.mod.classicadventure;

import net.minecraft.tags.BlockTags;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = CA.ID)
public class BreakHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onSpeed(PlayerEvent.BreakSpeed e) {
        if (e.getEntity().isCreative()) return;

        var s = e.getState();
        var h = e.getEntity().getMainHandItem();

        boolean invalid = (s.is(BlockTags.MINEABLE_WITH_PICKAXE) && !h.canPerformAction(ItemAbilities.PICKAXE_DIG)) ||
                         (s.is(BlockTags.MINEABLE_WITH_AXE) && !h.canPerformAction(ItemAbilities.AXE_DIG)) ||
                         (s.is(BlockTags.MINEABLE_WITH_SHOVEL) && !h.canPerformAction(ItemAbilities.SHOVEL_DIG)) ||
                         (s.is(BlockTags.MINEABLE_WITH_HOE) && !h.canPerformAction(ItemAbilities.HOE_DIG)) ||
                         (s.requiresCorrectToolForDrops() && !e.getEntity().hasCorrectToolForDrops(s));

        if (invalid) e.setNewSpeed(0f);
    }
}