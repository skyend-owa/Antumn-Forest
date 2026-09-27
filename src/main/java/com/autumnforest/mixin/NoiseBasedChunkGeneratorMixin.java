/*
package com.autumnforest.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static java.lang.Math.sin;

@Mixin(NoiseBasedChunkGenerator.class)

public abstract class NoiseBasedChunkGeneratorMixin {

    //在此位置注入
    @Inject (
            method = "buildTerrain",
            at = @At("RETURN"),
            cancellable = true
    )

    //生成函数
    private void autumnForestBuilder(
            ChunkAccess chunk,
            Blender blender,
            RandomState randomState,
            StructureManager structureManager,
            BiomeManager biomeManager,
            @Nullable WorldGenRegion carverBiomeRegion,
            Set<Holder<Biome>> possibleBiomes,
            CallbackInfoReturnable<CompletableFuture<ChunkAccess>> cir
    )
    {
        CompletableFuture<ChunkAccess> original = cir.getReturnValue();

        cir.setReturnValue(original.thenApply(generatedChunk -> {

            autumnForestRaise(
                    generatedChunk,
                    biomeManager
            );

            return generatedChunk;
        }));
    }

    private static void autumnForestRaise(
            ChunkAccess chunk,
            BiomeManager biomeManager
    ) {

        ChunkPos chunkPos = chunk.getPos();

        int startX = chunkPos.getMinBlockX();
        int startZ = chunkPos.getMinBlockZ();

        BlockPos.MutableBlockPos mutablePos =
                new BlockPos.MutableBlockPos();

        for (int localX = 0; localX < 16; localX++) {
            for (int localZ = 0; localZ < 16; localZ++) {

                int worldX = startX + localX;
                int worldZ = startZ + localZ;

                int surfaceY = chunk.getHeight(
                        Heightmap.Types.WORLD_SURFACE_WG,
                        localX,
                        localZ
                );

                Holder<Biome> biome =
                        biomeManager.getBiome(
                                worldX,
                                surfaceY,
                                worldZ
                        );

                if (!biome.is(Biomes.DAPPLED_FOREST)) {
                    continue;
                }

                // 故意做得非常明显。
                // 先证明我们确实改到了 Dappled Forest。
                double wave =
                        sin(worldX * 0.08)
                                + Math.cos(worldZ * 0.08);

                int extraHeight =
                        mountainHeight(worldX, worldZ);

                for (
                        int y = surfaceY;
                        y < surfaceY + extraHeight
                                && y < chunk.getMaxY();
                        y++
                ) {
                    mutablePos.set(worldX, y, worldZ);

                    chunk.setBlockState(
                            mutablePos,
                            Blocks.STONE.defaultBlockState(),
                            0
                    );
                }
            }
        }
    }

    private static int mountainHeight(int x, int z) {
        double largeScale =  Math.sin(x * 0.01);
        double secondary =  Math.sin(z * 0.02);

        double terrain = 30* Math.abs(largeScale + secondary);

        return (int) terrain;
    }
}

*/
