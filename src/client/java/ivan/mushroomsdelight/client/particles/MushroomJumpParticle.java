package ivan.mushroomsdelight.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class MushroomJumpParticle extends SingleQuadParticle {
    protected MushroomJumpParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, SpriteSet sprites) {
        super(level, x, y, z, sprites.get(0, 1));
        this.xd = xa;
        this.yd = ya;
        this.zd = za;
        this.lifetime = 50;
        this.gravity = 0.0f;
        this.friction = 0.97f;
        this.scale(1.5F);
        this.setSprite(sprites.get(this.random));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.alpha <= 0.05F) {
            this.alpha = 0.0F;
        } else {
            this.alpha -= 0.05F;
        }
        if (this.yd > 0.1) {
            this.yd -= 0.015;
        }
    }

    @Override
    protected Layer getLayer() {
        return Layer.bySprite(this.sprite);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(final SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(final SimpleParticleType options, final ClientLevel level, final double x, final double y, final double z, final double xAux, final double yAux, final double zAux, final RandomSource random) {
            return new MushroomJumpParticle(level, x, y, z, xAux, yAux, zAux, this.sprites);
        }
    }
}
