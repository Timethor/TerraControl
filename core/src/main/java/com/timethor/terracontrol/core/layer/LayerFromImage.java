package com.timethor.terracontrol.core.layer;

import com.timethor.terracontrol.core.TerraWorld;
import com.timethor.terracontrol.core.TerraControl;
import com.timethor.terracontrol.core.configuration.WorldConfig;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

/**
 * @author   Timethor
 */
public class LayerFromImage extends Layer {

    /**
	 */
    private int[] biomeMap;
    /**
	 */
    private int mapHeight;
    /**
	 */
    private int mapWidth;
    /**
	 */
    private int fillBiome = 0;
    /**
	 */
    private int xOffset;
    /**
	 */
    private int zOffset;
    /**
	 */
    private WorldConfig.ImageMode imageMode;

    /**
     *
     * @param paramLong
     * @param _child
     * @param config
     * @param world
     */
    public LayerFromImage(long paramLong, Layer _child, WorldConfig config, TerraWorld world) {
        super(paramLong);
        this.child = _child;
        xOffset = config.imageXOffset;
        zOffset = config.imageZOffset;
        this.imageMode = config.imageMode;
        this.fillBiome = world.getBiomeIdByName(config.imageFillBiome);

        //read from file

        try {
            File image = new File(config.file, config.imageFile);
            BufferedImage map = ImageIO.read(image);
            this.mapHeight = map.getHeight(null);
            this.mapWidth = map.getWidth(null);
            int[] colorMap = new int[this.mapHeight * this.mapWidth];
            this.biomeMap = new int[this.mapHeight * this.mapWidth];

            map.getRGB(0, 0, this.mapWidth, this.mapHeight, colorMap, 0, this.mapWidth);

            for (int i = 0; i < colorMap.length; i++) {
                int color = colorMap[i] & 0xFFFFFF;

                if (config.biomeColorMap.containsKey(color)) {
                    this.biomeMap[i] = config.biomeColorMap.get(color);
                } else {
                    this.biomeMap[i] = fillBiome;
                }
            }
        } catch (IOException ioexception) {
            TerraControl.log(Level.WARNING, ioexception.getStackTrace().toString());
        }

    }

    /**
     *
     * @param cacheId
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    @Override
    protected int[] GetBiomes(int cacheId, int x, int z, int x_size, int z_size) {
        int[] arrayOfInt1 = LayerCache.GetArray(cacheId, x_size * z_size);
        int[] arrayOfInt2 = null;

        if (this.child != null) {
            arrayOfInt2 = this.child.GetBiomes(cacheId, x, z, x_size, z_size);
        }

        int Buffer_x;
        int Buffer_z;

        for (int i = 0; i < z_size; i++) {
            for (int t = 0; t < x_size; t++) {
                //SetSeed(t + x, i + z);

                if (this.imageMode == WorldConfig.ImageMode.Repeat) {
                    Buffer_x = this.mapWidth - 1 - Math.abs((z + i - zOffset) % this.mapWidth);
                    Buffer_z = Math.abs((x + t - xOffset) % this.mapHeight);
                    arrayOfInt1[(t + i * x_size)] = this.biomeMap[Buffer_x + Buffer_z * this.mapWidth];
                } else {
                    Buffer_x = this.mapWidth - (z + i - zOffset);
                    Buffer_z = (x + t - xOffset);
                    if (Buffer_x < 0 || Buffer_x >= this.mapWidth || Buffer_z < 0 || Buffer_z >= this.mapHeight) {
                        if (arrayOfInt2 != null) {
                            arrayOfInt1[(t + i * x_size)] = arrayOfInt2[(t + i * x_size)];
                        } else {
                            arrayOfInt1[(t + i * x_size)] = this.fillBiome;
                        }
                    } else {
                        arrayOfInt1[(t + i * x_size)] = this.biomeMap[Buffer_x + Buffer_z * this.mapWidth];
                    }

                }
            }

        }


        return arrayOfInt1;
    }
}
