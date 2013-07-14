package com.timethor.terracontrol.core.util.coordinate;

import com.timethor.terracontrol.core.util.MathHelper;

public class Coordinate3D extends Coordinate2D {

    private int z;

    public Coordinate3D() {
    }

    public Coordinate3D(int x, int y) {
        super(x, y);
        this.z = 0;
    }

    public Coordinate3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Coordinate3D(Coordinate2D other, int z) {
        super(other);
        this.z = z;
    }

    public Coordinate3D(Coordinate3D other) {
        super(other);
        this.z = other.z;
    }

    //>>	END CONSTRUCTORS
    @Override
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    @Override
    public int magnitudeSqared() {
        return super.magnitudeSqared() + z * z;
    }

    public static <T extends Coordinate2D> T getGenericVector(T a) {
        if (a instanceof Coordinate3D) {
            return (T) new Coordinate3D();
        } else {
            return Coordinate2D.getGenericVector(a);
        }
    }

    public static Coordinate3D add(Coordinate3D a, Coordinate2D... b) {
        int x0 = a.getX();
        int y0 = a.getY();
        for (Coordinate2D b0 : b) {
            x0 += b0.getX();
            y0 += b0.getY();
        }
        return new Coordinate3D(x0, y0, a.z);
    }

    public static Coordinate3D add(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.getX();
        int y0 = a.getY();
        int z0 = a.z;
        for (Coordinate3D b0 : b) {
            x0 += b0.getX();
            y0 += b0.getY();
            z0 += b0.z;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    public static Coordinate3D sub(Coordinate3D a, Coordinate2D... b) {
        int x0 = a.getX();
        int y0 = a.getY();
        for (Coordinate2D b0 : b) {
            x0 -= b0.getX();
            y0 -= b0.getY();
        }
        return new Coordinate3D(x0, y0, a.z);
    }

    public static Coordinate3D sub(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.getX();
        int y0 = a.getY();
        int z0 = a.z;
        for (Coordinate3D b0 : b) {
            x0 -= b0.getX();
            y0 -= b0.getY();
            z0 -= b0.z;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    public static Coordinate3D shift(Coordinate3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Coordinate3D(MathHelper.floor_int(a.getX() + b1), MathHelper.floor_int(a.getY() + b1), MathHelper.floor_int(a.z + b1));
    }

    public static Coordinate3D mult(Coordinate3D a, Coordinate2D... b) {
        int x0 = a.getX();
        int y0 = a.getY();
        for (Coordinate2D b0 : b) {
            x0 *= b0.getX();
            y0 *= b0.getY();
        }
        return new Coordinate3D(x0, y0, a.z);
    }

    public static Coordinate3D mult(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.getX();
        int y0 = a.getY();
        int z0 = a.z;
        for (Coordinate3D b0 : b) {
            x0 *= b0.getX();
            y0 *= b0.getY();
            z0 *= b0.z;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    public static Coordinate3D mult(Coordinate3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Coordinate3D(MathHelper.floor_int(a.getX() * b1), MathHelper.floor_int(a.getY() * b1), MathHelper.floor_int(a.z * b1));
    }

    public static double dot(Coordinate3D a, Coordinate3D b) {
        return Coordinate2D.dot(a, b) + a.z * b.z;
    }

    public static Coordinate3D cross(Coordinate3D a, Coordinate3D b) {
        return new Coordinate3D(
            MathHelper.floor_int((a.getY() * b.getZ()) - (a.getZ() * b.getY())),
            MathHelper.floor_int((a.getZ() * b.getX()) - (a.getX() * b.getZ())),
            Coordinate2D.cross(a, b));
    }

    public static Coordinate3D div(Coordinate3D a, Coordinate2D... b) {
        int x0 = a.getX();
        int y0 = a.getY();
        for (Coordinate2D b0 : b) {
            x0 /= b0.getX();
            y0 /= b0.getY();
        }
        return new Coordinate3D(x0, y0, a.z);
    }

    public static Coordinate3D div(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.getX();
        int y0 = a.getY();
        int z0 = a.z;
        for (Coordinate3D b0 : b) {
            x0 /= b0.getX();
            y0 /= b0.getY();
            z0 /= b0.z;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    public static Coordinate3D div(Coordinate3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Coordinate3D(MathHelper.floor_int(a.getX() / b1), MathHelper.floor_int(a.getY() / b1), MathHelper.floor_int(a.z / b1));
    }

    public static Coordinate3D componentMax(Coordinate3D a, Coordinate3D... b) {
        a = (Coordinate3D) Coordinate2D.componentMax(a, b);
        for (Coordinate3D b0 : b) {
            a.z = a.z > b0.z ? a.z : b0.z;
        }
        return a;
    }

    public static Coordinate3D componentMin(Coordinate3D a, Coordinate3D... b) {
        a = (Coordinate3D) Coordinate2D.componentMax(a, b);
        for (Coordinate3D b0 : b) {
            a.z = a.z < b0.z ? a.z : b0.z;
        }
        return a;
    }

    public static Coordinate3D min(Coordinate3D a, Coordinate3D... b) {
        for (Coordinate3D b0 : b) {
            a = a.magnitudeSqared() < b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    public static Coordinate3D max(Coordinate3D a, Coordinate3D... b) {
        for (Coordinate3D b0 : b) {
            a = a.magnitudeSqared() >= b0.magnitudeSqared() ? a : b0;
        }
        return a;
    }

    public static Coordinate3D clamp(Coordinate3D a, Coordinate3D min, Coordinate3D max) {
        a = (Coordinate3D) Coordinate2D.clamp(a, min, max);
        a.z = a.z < min.z ? min.z : (a.z > max.z ? max.z : a.z);
        return a;
    }

    public static Coordinate3D clamp(Coordinate3D a, int min, int max) {
        a = (Coordinate3D) Coordinate2D.clamp(a, min, max);
        a.z = a.z < min ? min : (a.z > max ? max : a.z);
        return a;
    }

    public static Coordinate3D lerp(Coordinate3D a, Coordinate3D b, int blend) {
        a = (Coordinate3D) Coordinate2D.lerp(a, b, blend);
        a.z = MathHelper.floor_int(blend * (b.z - a.z) + a.z);
        return a;
    }

    public static Coordinate3D baryCentric(Coordinate3D a, Coordinate3D b, Coordinate3D c, int x, int y) {
        //>>	a+x * (b-a) + v * (c-a)
        return Coordinate3D.add(Coordinate3D.mult(Coordinate3D.shift(a, x), Coordinate3D.sub(b, a)), Coordinate3D.mult(Coordinate3D.sub(c, a), y));
    }

    public static double angle(Coordinate3D a, Coordinate3D b) {
        return Math.acos(Coordinate3D.dot(a, b)) / (a.magnitude() * b.magnitude());
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + this.z;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Coordinate3D) {
            final Coordinate3D other = (Coordinate3D) obj;
            if (this.z != other.z) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
