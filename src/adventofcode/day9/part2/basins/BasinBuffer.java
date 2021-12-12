package adventofcode.day9.part2.basins;

import java.util.List;
import java.util.Objects;

public class BasinBuffer {
    private final List<BasinSector> sectors;

    public BasinBuffer(List<BasinSector> sectors) {
        this.sectors = sectors;
    }

    public List<BasinSector> getSectors() {
        return sectors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasinBuffer that = (BasinBuffer) o;
        return Objects.equals(sectors, that.sectors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectors);
    }
}
