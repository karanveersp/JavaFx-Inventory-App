package inventorymanager.test;

import inventorymanager.model.InHouse;
import inventorymanager.model.Inventory;
import inventorymanager.model.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    Inventory inv;

    @BeforeEach
    public void setUp() {
        inv = new Inventory();
        inv.addPart(new InHouse(1, "a", 10, 10, 1, 10, 10));
        inv.addPart(new InHouse(2, "b", 10, 10, 1, 10, 10));
        inv.addPart(new InHouse(3, "c", 10, 10, 1, 10, 10));
        inv.addPart(new InHouse(4, "c", 10, 10, 1, 10, 12));
    }

    @Test
    public void lookupPartByNameWorks() {
        // arrange
        ObservableList<Part> expected = FXCollections.observableArrayList(
                new InHouse(3, "c", 10, 10, 1, 10, 10),
                new InHouse(4, "c", 10, 10, 1, 10, 12)
        );

        // act
        ObservableList<Part> result = inv.lookupPart("c");

        // assert
        assertEquals(expected.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i), expected.get(i));
        }
    }

    @Test
    public void lookupPartByIdWorks() {
        // arrange
        Part expected = new InHouse(2, "b", 10, 10, 1, 10, 10);

        // act
        Part result = inv.lookupPart(2);

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void deletePartWorks() {
        boolean result = inv.deletePart(new InHouse(2, "b", 10, 10, 1, 10, 10));
        assertTrue(result);
        assertEquals(3, inv.getAllParts().size());
    }

}