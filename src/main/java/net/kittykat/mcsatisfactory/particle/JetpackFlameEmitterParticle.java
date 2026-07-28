package net.kittykat.mcsatisfactory.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class JetpackFlameEmitterParticle extends NoRenderParticle {
    private static final byte  PARTICLE_COUNT = 5;
    private static final float PARTICLE_SPEED     = .35f;
    private static final float SPEED_VARIATION    = .05f;
    private static final float PARTICLE_DEVIATION = .04f;

    protected JetpackFlameEmitterParticle(ClientWorld clientWorld, double x, double y, double z) {
        super(clientWorld, x, y, z, 0d, 0d, 0d);
    }

    @Override
    public void tick() {
        for (int i = 0; i < PARTICLE_COUNT; i++) {
            world.addParticle(ModParticles.JETPACK_FLAME, x, y, z,
                    MathHelper.nextGaussian(random, 0f, PARTICLE_DEVIATION),
                    MathHelper.nextFloat(random, -SPEED_VARIATION, SPEED_VARIATION) - PARTICLE_SPEED,
                    MathHelper.nextGaussian(random, 0f, PARTICLE_DEVIATION));
        }
        markDead();
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld,
                                       double x, double y, double z, double velX, double velY, double velZ) {
            return new JetpackFlameEmitterParticle(clientWorld, x, y, z);
        }
    }
}
