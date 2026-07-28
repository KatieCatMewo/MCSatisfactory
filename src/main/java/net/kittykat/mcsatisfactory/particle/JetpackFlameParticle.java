package net.kittykat.mcsatisfactory.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class JetpackFlameParticle extends AnimatedPhysicsParticle {
    protected JetpackFlameParticle(ClientWorld world, double x, double y, double z,
                                   double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ, 0f, 1f, 1f, spriteProvider);
        this.alphaFade = false;
        this.maxAge = 5 + this.random.nextInt(3);
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public float getSize(float tickDelta) {
        float f = ((float)this.age + tickDelta) / (float)this.maxAge;
        return this.scale * (1.0F - f * f * 0.5F);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;
        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld,
                                       double x, double y, double z, double velX, double velY, double velZ) {
            return new JetpackFlameParticle(clientWorld, x, y, z, velX, velY, velZ, this.spriteProvider);
        }
    }
}
