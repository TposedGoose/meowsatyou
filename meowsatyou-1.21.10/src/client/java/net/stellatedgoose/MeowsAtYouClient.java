package net.stellatedgoose;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.stellatedgoose.commands.Meow;

import static net.stellatedgoose.MeowsAtYou.LOGGER;

public class MeowsAtYouClient implements ClientModInitializer {
	public static int timesMeowed = 0;
	public static Random random = Random.create();

	public static void meow(String meowText) {
		LOGGER.info("meows at you (" + timesMeowed + ")");
		MinecraftClient c = MinecraftClient.getInstance();

		if (c.player != null) {
			if (random.nextBoolean()) {
				c.player.playSound(SoundEvents.ENTITY_CAT_AMBIENT, 0.8F, random.nextFloat()*0.5f + 0.75f);
			} else if (random.nextBoolean()) {
				c.player.playSound(SoundEvents.ENTITY_CAT_PURREOW, 1F, random.nextFloat()*0.5f + 0.75f);
			} else {
				c.player.playSound(SoundEvents.ENTITY_CAT_PURR, 1F, random.nextFloat()*0.5f + 0.75f);
			}

            assert c.world != null;
//            if (Objects.requireNonNull(c.world.getServer()).getServerIp().contains("hollowcube"))
//				c.player.sendMessage(Text.literal("\uE1EE seals at you \uE1EE").setStyle(Style.EMPTY.withItalic(true).withColor(random.nextInt(16777215))), false);
//			else
			c.player.sendMessage(Text.literal(meowText).setStyle(Style.EMPTY.withItalic(true).withColor(random.nextInt(16777215))), false);
		}
	}

	public static void tryMeow(ClientWorld world) {
		if (world.getTime() % 100 == 0) {
			int number = random.nextBetween(1, 459);
			if (timesMeowed==0) {
				timesMeowed++;
				meow("meow");
			} else if (number == 459) {
				timesMeowed++;
				meow("meows at you");
			}
		}
	}

	public static void tryMeow(int max, boolean alwaysMeow) {
		if (alwaysMeow) {
			timesMeowed++;
			meow("meow");
		} else {
			int number = random.nextBetween(1, max);
			if (number == 7) {
				timesMeowed++;
				meow("meows at you");
			}
		}
	}
	@Override
	public void onInitializeClient() {
		LOGGER.info("meows at you");
		ClientCommandRegistrationCallback.EVENT.register(MeowsAtYouClient::registerCommands);
		ClientTickEvents.END_WORLD_TICK.register(MeowsAtYouClient::tryMeow);

	}


	private static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess) {
		Meow.register(dispatcher);
	}
}