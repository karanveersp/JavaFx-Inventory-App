package inventorymanager.model;

import java.util.Objects;

public class InHouse extends Part {
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }


    @Override
    public String toString() {
        return "InHouse{" +
                "machineId=" + machineId +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InHouse inHouse = (InHouse) o;
        return machineId == inHouse.machineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), machineId);
    }
}
