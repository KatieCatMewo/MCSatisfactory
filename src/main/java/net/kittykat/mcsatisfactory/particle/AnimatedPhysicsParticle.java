package net.kittykat.mcsatisfactory.particle;

import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;

public abstract class AnimatedPhysicsParticle extends AnimatedParticle {
    protected boolean followLighting = false;
    protected boolean alphaFade = true;
    protected AnimatedPhysicsParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ,
                                      float groundFrictionFactor, float initialVelMultiplier, float gravityFactor, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, gravityFactor);
        this.velocityX = velocityX * initialVelMultiplier;
        this.velocityY = velocityY * initialVelMultiplier;
        this.velocityZ = velocityZ * initialVelMultiplier;
        this.velocityMultiplier = groundFrictionFactor;
        this.collidesWithWorld = true;
    }

    @Override
    public int getBrightness(float tint) {
        if (followLighting) {
            BlockPos blockPos = BlockPos.ofFloored(x, y, z);
            return (this.world.isChunkLoaded(blockPos)) ? WorldRenderer.getLightmapCoordinates(this.world, blockPos) : 0;
        } else {
            return super.getBrightness(tint);
        }
    }

    @Override
    public void move(double dx, double dy, double dz) {
        //super.move(dx, dy, dz);
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;
        setBoundingBox(getBoundingBox().offset(dx, dy, dz));
        repositionFromBoundingBox();
    }

    @Override
    public void tick() {
        if (age++ >= maxAge) {
            markDead();
        } else {
            move(velocityX, velocityY, velocityZ);
        }
        setSpriteForAge(spriteProvider);
        if ((age > maxAge / 2) && alphaFade) {
            setAlpha(1.0F - ((float)this.age - (float)(this.maxAge / 2)) / (float)this.maxAge);
        }
    }

    @Override
    public void setTargetColor(int rgbHex) {
        LOGGER.warn("{} does not support color change!", this.getClass().getSimpleName());
    }
}
