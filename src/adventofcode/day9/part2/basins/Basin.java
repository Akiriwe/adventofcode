package adventofcode.day9.part2.basins;

import java.util.List;

public class Basin implements Comparable<Basin> {
    private final List<BasinSector> sectors;

    public Basin(List<BasinSector> sectors) {
        this.sectors = sectors;
    }

    public void addAll(List<BasinSector> newSectors) {
        sectors.addAll(newSectors);
    }

    public List<BasinSector> getSectors() {
        return sectors;
    }

    @Override
    public String toString() {
        return "Basin{" +
                "sectors=" + sectors +
                '}';
    }

    @Override
    public int compareTo(Basin o) {
        return this.getSectors().size() - o.getSectors().size();
    }
}
