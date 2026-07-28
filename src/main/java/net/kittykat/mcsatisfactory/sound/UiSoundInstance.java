package net.kittykat.mcsatisfactory.sound;

import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;

public class UiSoundInstance extends AbstractSoundInstance {
    protected UiSoundInstance(SoundEvent sound, SoundCategory category, Random random) {
        super(sound, category, random);
        attenuationType = AttenuationType.NONE;
    }
}
