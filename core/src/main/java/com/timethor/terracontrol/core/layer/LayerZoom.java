package com.timethor.terracontrol.core.layer;

/**
 *
 * @author Timethor
 */
public class LayerZoom extends Layer {

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     */
    public LayerZoom(long paramLong, Layer paramGenLayer) {
        super(paramLong);
        this.child = paramGenLayer;
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
    public int[] GetBiomes(int cacheId, int x, int z, int x_size, int z_size) {
        int i = x >> 1;
        int j = z >> 1;
        int k = (x_size >> 1) + 3;
        int m = (z_size >> 1) + 3;
        int[] arrayOfInt1 = this.child.GetBiomes(cacheId, i, j, k, m);

        int[] arrayOfInt2 = LayerCache.GetArray(cacheId, k * 2 * (m * 2));
        int n = k << 1;
        int i2;
        for (int i1 = 0; i1 < m - 1; i1++) {
            i2 = i1 << 1;
            int i3 = i2 * n;
            int i4 = arrayOfInt1[(0 + (i1 + 0) * k)];
            int i5 = arrayOfInt1[(0 + (i1 + 1) * k)];
            for (int i6 = 0; i6 < k - 1; i6++) {
                SetSeed((long) (i6 + i << 1), (long) (i1 + j << 1));
                int i7 = arrayOfInt1[(i6 + 1 + (i1 + 0) * k)];
                int i8 = arrayOfInt1[(i6 + 1 + (i1 + 1) * k)];

                arrayOfInt2[i3] = i4;
                arrayOfInt2[i3++ + n] = RndParam(i4, i5);
                arrayOfInt2[i3] = RndParam(i4, i7);
                arrayOfInt2[i3++ + n] = b(i4, i7, i5, i8);

                i4 = i7;
                i5 = i8;
            }
        }
        int[] arrayOfInt3 = LayerCache.GetArray(cacheId, x_size * z_size);
        for (i2 = 0; i2 < z_size; i2++) {
            System.arraycopy(arrayOfInt2, (i2 + (z & 0x1)) * (k << 1) + (x & 0x1), arrayOfInt3, i2 * x_size, x_size);
        }
        return arrayOfInt3;
    }

    /**
     *
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    protected int RndParam(int paramInt1, int paramInt2) {
        return nextInt(2) == 0 ? paramInt1 : paramInt2;
    }

    /**
     *
     * @param x
     * @param z
     * @param x_size
     * @param z_size
     * @return
     */
    protected int b(int x, int z, int x_size, int z_size) {
        if ((z == x_size) && (x_size == z_size)) {
            return z;
        }
        if ((x == z) && (x == x_size)) {
            return x;
        }
        if ((x == z) && (x == z_size)) {
            return x;
        }
        if ((x == x_size) && (x == z_size)) {
            return x;
        }

        if ((x == z) && (x_size != z_size)) {
            return x;
        }
        if ((x == x_size) && (z != z_size)) {
            return x;
        }
        if ((x == z_size) && (z != x_size)) {
            return x;
        }

        if ((z == x_size) && (x != z_size)) {
            return z;
        }
        if ((z == z_size) && (x != x_size)) {
            return z;
        }

        if ((x_size == z_size) && (x != z)) {
            return x_size;
        }


        int i = nextInt(4);
        if (i == 0) {
            return x;
        }
        if (i == 1) {
            return z;
        }
        if (i == 2) {
            return x_size;
        }
        return z_size;
    }

    /**
     *
     * @param paramLong
     * @param paramGenLayer
     * @param paramInt
     * @return
     */
    public static Layer a(long paramLong, Layer paramGenLayer, int paramInt) {
        Layer localObject = paramGenLayer;
        for (int i = 0; i < paramInt; i++) {
            localObject = new LayerZoom(paramLong + i, localObject);
        }
        return localObject;
    }
}