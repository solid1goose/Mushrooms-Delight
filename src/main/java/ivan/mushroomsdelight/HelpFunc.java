package ivan.mushroomsdelight;

import net.minecraft.util.RandomSource;

public class HelpFunc {
    public static float getRandomFloatFromTo(float min, float max, RandomSource randomSource) {
        return min + randomSource.nextFloat() * (max - min);
    };
}
