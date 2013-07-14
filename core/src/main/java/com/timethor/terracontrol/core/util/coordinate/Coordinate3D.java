package com.timethor.terracontrol.core.util.coordinate;

import com.timethor.terracontrol.core.util.MathHelper;

public class Coordinate3D extends Coordinate2D {

    protected int y;

    public Coordinate3D() {
    }

    public Coordinate3D(int x, int z) {
        super(x, z);
        this.y = 0;
    }

    public Coordinate3D(int x, int y, int z) {
        super(x, z);
        this.y = y;
    }

    public Coordinate3D(Coordinate2D other, int y) {
        super(other);
        this.y = y;
    }

    public Coordinate3D(Coordinate3D other) {
        super(other);
        this.y = other.y;
    }

    //>>	END CONSTRUCTORS
    @Override
    public double magnitude() {
        return Math.sqrt(magnitudeSqared());
    }

    @Override
    public int magnitudeSqared() {
        return super.magnitudeSqared() + y * y;
    }

    public static <T extends Coordinate2D> T getGenericVector(T a) {
        if (a instanceof Coordinate3D) {
            return (T) new Coordinate3D();
        } else {
            return Coordinate2D.getGenericVector(a);
        }
    }

    public static Coordinate3D add(Coordinate3D a, Coordinate2D... b) {
        int x0 = a.x;
        int y0 = a.z;
        for (Coordinate2D b0 : b) {
            x0 += b0.x;
            y0 += b0.z;
        }
        return new Coordinate3D(x0, y0, a.y);
    }

    public static Coordinate3D add(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.x;
        int y0 = a.z;
        int z0 = a.y;
        for (Coordinate3D b0 : b) {
            x0 += b0.x;
            y0 += b0.z;
            z0 += b0.y;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    public static Coordinate3D sub(Coordinate3D a, Coordinate2D... b) {
        int x0 = a.x;
        int y0 = a.z;
        for (Coordinate2D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.z;
        }
        return new Coordinate3D(x0, y0, a.y);
    }

    public static Coordinate3D sub(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.x;
        int y0 = a.z;
        int z0 = a.y;
        for (Coordinate3D b0 : b) {
            x0 -= b0.x;
            y0 -= b0.z;
            z0 -= b0.y;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    public static Coordinate3D shift(Coordinate3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 += b0;
        }
        return new Coordinate3D(MathHelper.floor_int(a.x + b1), MathHelper.floor_int(a.z + b1), MathHelper.floor_int(a.y + b1));
    }

    public static Coordinate3D mult(Coordinate3D a, Coordinate2D... b) {
        int x0 = a.x;
        int z0 = a.z;
        for (Coordinate2D b0 : b) {
            x0 *= b0.x;
            z0 *= b0.z;
        }
        return new Coordinate3D(x0, z0, a.y);
    }

    public static Coordinate3D mult(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.x;
        int y0 = a.z;
        int z0 = a.y;
        for (Coordinate3D b0 : b) {
            x0 *= b0.x;
            y0 *= b0.z;
            z0 *= b0.y;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    public static Coordinate3D mult(Coordinate3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 *= b0;
        }
        return new Coordinate3D(MathHelper.floor_int(a.x * b1), MathHelper.floor_int(a.z * b1), MathHelper.floor_int(a.y * b1));
    }

    public static double dot(Coordinate3D a, Coordinate3D b) {
        return Coordinate2D.dot(a, b) + a.y * b.y;
    }

    public static Coordinate3D cross(Coordinate3D a, Coordinate3D b) {
        return new Coordinate3D(
            MathHelper.floor_int((a.z * b.y) - (a.y * b.z)),
            MathHelper.floor_int((a.y * b.x) - (a.x * b.y)),
            Coordinate2D.cross(a, b));
    }

    public static Coordinate3D div(Coordinate3D a, Coordinate2D... b) {
        int x0 = a.x;
        int y0 = a.z;
        for (Coordinate2D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.z;
        }
        return new Coordinate3D(x0, y0, a.y);
    }

    public static Coordinate3D div(Coordinate3D a, Coordinate3D... b) {
        int x0 = a.x;
        int y0 = a.z;
        int z0 = a.y;
        for (Coordinate3D b0 : b) {
            x0 /= b0.x;
            y0 /= b0.z;
            z0 /= b0.y;
        }
        return new Coordinate3D(x0, y0, z0);
    }

    public static Coordinate3D div(Coordinate3D a, int... b) {
        double b1 = 0;
        for (double b0 : b) {
            b1 /= b0;
        }
        return new Coordinate3D(MathHelper.floor_int(a.x / b1), MathHelper.floor_int(a.z / b1), MathHelper.floor_int(a.y / b1));
    }

    public static Coordinate3D componentMax(Coordinate3D a, Coordinate3D... b) {
        a = (Coordinate3D) Coordinate2D.componentMax(a, b);
        for (Coordinate3D b0 : b) {
            a.y = a.y > b0.y ? a.y : b0.y;
        }
        return a;
    }

    public static Coordinate3D componentMin(Coordinate3D a, Coordinate3D... b) {
        a = (Coordinate3D) Coordinate2D.componentMax(a, b);
        for (Coordinate3D b0 : b) {
            a.y = a.y < b0.y ? a.y : b0.y;
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
        a.y = a.y < min.y ? min.y : (a.y > max.y ? max.y : a.y);
        return a;
    }

    public static Coordinate3D clamp(Coordinate3D a, int min, int max) {
        a = (Coordinate3D) Coordinate2D.clamp(a, min, max);
        a.y = a.y < min ? min : (a.y > max ? max : a.y);
        return a;
    }

    public static Coordinate3D lerp(Coordinate3D a, Coordinate3D b, int blend) {
        a = (Coordinate3D) Coordinate2D.lerp(a, b, blend);
        a.y = MathHelper.floor_int(blend * (b.y - a.y) + a.y);
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
        hash = 29 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Coordinate3D) {
            final Coordinate3D other = (Coordinate3D) obj;
            if (this.y != other.y) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
