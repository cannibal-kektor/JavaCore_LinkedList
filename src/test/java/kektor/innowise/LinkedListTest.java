package kektor.innowise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
    }

    @Test
    void testSizeIsEmpty() {
        assertEquals(0, list.size());
    }

    @Test
    void sizeShouldBeOneAfterAddingSingleElement() {
        list.addFirst(7);
        assertEquals(1, list.size());
    }

    @Test
    void getFirstShouldThrowExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
    }

    @Test
    void getFirstTest() {
        list.addFirst(7);
        assertEquals(7, list.getFirst());
    }

    @Test
    void getLastShouldThrowExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.getLast());
    }

    @Test
    void getLastTest() {
        list.addFirst(7);
        assertEquals(7, list.getLast());
    }

    @Test
    void getShouldThrowExceptionWhenEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void getShouldReturnElementWhenSingleElement() {
        list.addFirst(7);
        assertEquals(7, list.get(0));
    }

    @Test
    void getShouldReturnElement() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void getShouldThrowExceptionWhenIndexNegative() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void getShouldThrowExceptionWhenIndexGreaterOrEqualSize() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    void addFirstTest() {
        list.addFirst(10);
        list.addFirst(20);

        assertEquals(20, list.getFirst());
        assertEquals(10, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void addLastTest() {
        list.addLast(10);
        list.addLast(20);

        assertEquals(10, list.getFirst());
        assertEquals(20, list.getLast());
        assertEquals(2, list.size());
    }


    @Test
    void addShouldInsertElementAtIndex() {
        list.addLast(10);
        list.addLast(30);
        list.add(1, 20);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void addAtIndexZeroTest() {
        list.addLast(10);
        list.add(0, 20);

        assertEquals(20, list.getFirst());
        assertEquals(10, list.getLast());
        assertEquals(2, list.size());
    }


    @Test
    void addAtLastIndexTest() {
        list.addLast(10);
        list.add(1, 20);

        assertEquals(10, list.getFirst());
        assertEquals(20, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void addShouldThrowExceptionWhenIndexInvalid() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 10));
    }

    @Test
    void removeFirstShouldThrowExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    }

    @Test
    void removeFirstShouldReturnElementAndEmptyList() {
        list.addFirst(7);
        Integer result = list.removeFirst();
        assertEquals(7, result);
        assertEquals(0, list.size());
    }

    @Test
    void removeFirstShouldRemoveFirstElement() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);

        Integer result = list.removeFirst();

        assertEquals(10, result);
        assertEquals(20, list.getFirst());
        assertEquals(3, list.size());
    }


    @Test
    void removeLastShouldThrowExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> list.removeLast());
    }

    @Test
    void removeLastShouldReturnElementAndEmptyList() {
        list.addFirst(7);
        Integer result = list.removeLast();
        assertEquals(7, result);
        assertEquals(0, list.size());
    }

    @Test
    void removeLastShouldRemoveLastElement() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);

        Integer result = list.removeLast();

        assertEquals(40, result);
        assertEquals(30, list.getLast());
        assertEquals(3, list.size());
    }

    @Test
    void removeShouldThrowExceptionWhenEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void removeShouldReturnElementWhenSingleElement() {
        list.addFirst(7);
        Integer result = list.remove(0);
        assertEquals(7, result);
        assertEquals(0, list.size());
    }

    @Test
    void removeShouldRemoveElementAtIndex() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);

        Integer result = list.remove(2);

        assertEquals(30, result);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(40, list.get(2));
        assertEquals(3, list.size());

        result = list.remove(list.size() - 1);
        assertEquals(40, result);
    }

    @Test
    void removeShouldThrowExceptionWhenIndexInvalid() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
    }

    @Test
    void multipleOperationsTest() {
        list.addFirst(1);
        list.addLast(3);
        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
        assertEquals(2, list.get(1));

        Integer first = list.removeFirst();
        Integer last = list.removeLast();

        assertEquals(1, first);
        assertEquals(3, last);
        assertEquals(1, list.size());
        assertEquals(2, list.getFirst());
        assertEquals(2, list.getLast());

        list.remove(0);
        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
        assertThrows(NoSuchElementException.class, () -> list.getLast());
    }

    @Test
    void largeNumberOfOperationsTest() {
        for (int i = 0; i < 100; i++) {
            list.addLast(i);
        }
        assertEquals(100, list.size());

        for (int i = 50; i < 75; i++) {
            list.remove(50);
        }
        assertEquals(75, list.size());

        assertEquals(0, list.get(0));
        assertEquals(49, list.get(49));
        assertEquals(75, list.get(50));
    }

}
