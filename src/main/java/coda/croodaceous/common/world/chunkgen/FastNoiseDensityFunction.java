package coda.croodaceous.common.world.chunkgen;

import coda.croodaceous.common.util.FastNoise;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import java.util.function.Function;

public class FastNoiseDensityFunction implements DensityFunction.SimpleFunction {
    final FastNoise noise;
    final Function<NoiseContext, Double> transformer;

    protected FastNoiseDensityFunction(FastNoise noise, Function<NoiseContext, Double> transformer) {
        this.noise = noise;
        this.transformer = transformer;
    }

    protected FastNoiseDensityFunction(FastNoise noise) {
        this.noise = noise;
        this.transformer = (noiseContext -> (double) noiseContext.noise.GetSimplex((float) noiseContext.x, (float) noiseContext.y, (float) noiseContext.z));
    }

    protected FastNoiseDensityFunction(FastNoise noise, float offset) {
        this.noise = noise;
        this.transformer = (noiseContext -> (double) noiseContext.noise.GetSimplex((float) noiseContext.x, (float) noiseContext.y + offset, (float) noiseContext.z));
    }

    @Override
    public double compute(FunctionContext context) {
        return transformer.apply(new NoiseContext(this.noise, context.blockX(), context.blockY(), context.blockZ()));
    }

    @Override
    public double minValue() {
        return -1;
    }

    @Override
    public double maxValue() {
        return 1;
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return null;
    }

    protected record NoiseContext(FastNoise noise, double x, double y, double z) {}
}