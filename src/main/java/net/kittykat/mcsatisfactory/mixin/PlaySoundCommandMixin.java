package net.kittykat.mcsatisfactory.mixin;

import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.PlaySoundCommand;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * this is just a fix for an actual minecraft bug >w<
 * <br><br>
 * normally,{@link PlaySoundCommand}creates a new{@link SoundEvent}and{@link RegistryEntry}for the sound being played
 * instead of just getting them from{@link Registries}.<br>
 * this results in the{@code SoundEvent.distanceToTravel}field being completely ignored and defaulted back
 * to 16 blocks regardless of what the{@link SoundEvent}was actually registered with.
**/
@Mixin(PlaySoundCommand.class)
public class PlaySoundCommandMixin {
    @Redirect(method = "execute", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/sound/SoundEvent;of(Lnet/minecraft/util/Identifier;)Lnet/minecraft/sound/SoundEvent;"))
    private static SoundEvent fixSoundEvent(Identifier id) {
        // get the actual SoundEvent from the Registry instead of making a new one
        return Registries.SOUND_EVENT.get(id);
    }
    @Redirect(method = "execute", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/registry/entry/RegistryEntry;of(Ljava/lang/Object;)Lnet/minecraft/registry/entry/RegistryEntry;"))
    private static <T> RegistryEntry<SoundEvent> fixRegistryEntry(T value) {
        // get the RegistryEntry of the SoundEvent instead of creating one
        return Registries.SOUND_EVENT.getEntry((SoundEvent) value);
    }
}
