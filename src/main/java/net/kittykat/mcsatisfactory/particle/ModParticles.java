package net.kittykat.mcsatisfactory.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry.PendingParticleFactory;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.kittykat.mcsatisfactory.MCSatisfactory.MOD_ID;
import static net.kittykat.mcsatisfactory.MCSatisfactory.LOGGER;

public abstract class ModParticles {
    private static final ParticleFactoryRegistry FACTORY_REGISTRY = ParticleFactoryRegistry.getInstance();

    public static final DefaultParticleType JETPACK_FLAME         = FabricParticleTypes.simple(true);
    public static final DefaultParticleType JETPACK_FLAME_EMITTER = FabricParticleTypes.simple(true);

    public static void registerParticles() { // called from ModInitializer
        LOGGER.debug("registering particles for {}", MOD_ID);

        registerParticle("jetpack_flame",         JETPACK_FLAME);
        registerParticle("jetpack_flame_emitter", JETPACK_FLAME_EMITTER);
    }
    @Environment(EnvType.CLIENT)
    public static void registerFactories() { // called from ClientModInitializer
        LOGGER.debug("registering particle factories for {}", MOD_ID);

        registerFactory(JETPACK_FLAME,         JetpackFlameParticle.Factory::new);
        registerEmitter(JETPACK_FLAME_EMITTER, new JetpackFlameEmitterParticle.Factory());
    }

    private static void registerParticle(String id, ParticleType<? extends ParticleEffect> particle) {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, id), particle);
    }

    @Environment(EnvType.CLIENT)
    private static <P extends ParticleEffect> void registerFactory(ParticleType<P> type, PendingParticleFactory<P> factory) {
        FACTORY_REGISTRY.register(type, factory);
    }
    @Environment(EnvType.CLIENT)
    private static <P extends ParticleEffect> void registerEmitter(ParticleType<P> type, ParticleFactory<P> emitterFactory) {
        FACTORY_REGISTRY.register(type, emitterFactory);
    }
}
