package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final Resume RESUME1 = new Resume("uuid1");
    private static final Resume RESUME2 = new Resume("uuid2");
    private static final Resume RESUME3 = new Resume("uuid3");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    void update() throws NotExistStorageException {
        storage.update(RESUME1);
        storage.get("uuid1");
        assertSame(RESUME1, storage.get("uuid1"), "Testing update method");

        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid4")));
    }

    @Test
    void save() {
        Resume[] expected = {RESUME1, RESUME2, RESUME3};
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);
        assertThrows(ExistStorageException.class,
                () -> storage.save(RESUME2), "If resume exists should thrown");
        storage.clear();
        StorageException exception = assertThrows(StorageException.class,
                () -> {
                    for (int i = 0; i <= 10000; i++) {
                        storage.save(new Resume("uuid" + i));
                    }
                });
        assertEquals("Storage overflow", exception.getMessage());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void testSize() {
        assertEquals(3, storage.size());
    }

    @Test
    void get() {
        assertEquals(new Resume("uuid1"), storage.get("uuid1"));
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid4")));
    }

    @Test
    void delete() {
        storage.delete("uuid2");
        Resume[] expected = {RESUME1, RESUME3};
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);
        assertEquals(2, storage.size());
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid4")));
    }

    @Test
    void getAll() {
        Resume[] expectedResume = {RESUME1, RESUME2, RESUME3};
        assertArrayEquals(expectedResume, storage.getAll(), "Testing method 'getAll'");
    }
}
