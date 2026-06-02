package ivan.mushroomsdelight.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class SmokeParticle extends SingleQuadParticle {
    protected SmokeParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, SpriteSet sprites) {
        super(level, x, y, z, sprites.get(0, 1));
        this.xd = xa;
        this.yd = ya;
        this.zd = za;
        this.lifetime = 150;
        this.gravity = 0.0f;
        this.friction = 0.97f;
        this.scale(2.0F);
    }

    @Override
    protected Layer getLayer() {
        return Layer.bySprite(this.sprite);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.alpha <= 0.0F) {
            return;
        }
        if (this.age >= this.lifetime - 60) {
            this.alpha -= 0.015F;
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(final SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(final SimpleParticleType options, final ClientLevel level, final double x, final double y, final double z, final double xAux, final double yAux, final double zAux, final RandomSource random) {
            return new SmokeParticle(level, x, y, z, xAux, yAux, zAux, this.sprites);
        }
    }
}
