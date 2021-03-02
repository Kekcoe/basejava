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


    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    void update() {
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        storage.update(resume4);
        assertSame(resume4, storage.get("uuid4"), "Testing update method");
    }

    @Test
    void testNotExistStorageExceptionWhenUpdate() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("uuid4")));
    }

    @Test
    void save() {
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        Resume[] expected = {RESUME1, RESUME2, RESUME3, resume4};
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void testExistStorageExceptionWhenSave() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME2), "If resume exists should thrown");
    }

    @Test
    void testOverflowStorageExceptionWhenSave() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail(e.getMessage());
        }
        assertThrows(StorageException.class, () -> {
            storage.save(new Resume());
        });
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
    }

    @Test
    void testNotExistStorageExceptionWhenGet() {
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid4"));
    }

    @Test
    void delete() {
        storage.delete("uuid2");
        Resume[] expected = {RESUME1, RESUME3};
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);
        assertEquals(2, storage.size());
    }

    @Test
    void testNotExistStorageExceptionWhenDelete() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("uuid4"));
    }

    @Test
    void getAll() {
        Resume[] expectedResume = {RESUME1, RESUME2, RESUME3};
        assertArrayEquals(expectedResume, storage.getAll(), "Testing method 'getAll'");
    }
}
