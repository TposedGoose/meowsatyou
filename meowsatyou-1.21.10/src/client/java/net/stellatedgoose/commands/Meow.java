package net.stellatedgoose.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.stellatedgoose.MeowsAtYouClient.tryMeow;

public class Meow {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("meow")
                .executes(ctx -> meow(ctx.getSource())));
    }

    private static int meow(FabricClientCommandSource source) {
        tryMeow(100, true);
        return Command.SINGLE_SUCCESS;
    }
}