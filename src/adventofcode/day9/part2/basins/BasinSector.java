package adventofcode.day9.part2.basins;

import java.util.Objects;

public class BasinSector {
    private final int i;
    private final int j;
    private final int height;

    public BasinSector(int i, int j, int height) {
        this.i = i;
        this.j = j;
        this.height = height;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasinSector that = (BasinSector) o;
        return i == that.i && j == that.j && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, height);
    }

    @Override
    public String toString() {
        return "BasinSector{" +
                "i=" + i +
                ", j=" + j +
                ", height=" + height +
                '}';
    }
}
