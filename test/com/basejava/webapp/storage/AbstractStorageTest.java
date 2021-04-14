package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractStorageTest {
    protected final Storage storage;
    private static final Resume RESUME1 = new Resume("Friedrich Nietzsche", "uuid1");
    private static final Resume RESUME2 = new Resume("Zhliga Chmut", "uuid2");
    private static final Resume RESUME3 = new Resume("Nikolay Lossky", "uuid3");
    private static final Resume RESUME4 = new Resume("Friedrich Nietzsche", "uuid4");


    protected AbstractStorageTest(Storage storage) {
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
        storage.update(RESUME1);
        assertSame(RESUME1, storage.get("uuid1"), "Testing update method");
    }

    @Test
    void testNotExistStorageExceptionWhenUpdate() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("Friedrich Nietzsche", "uuid4")));
    }

    @Test
    void testSave() {
        storage.save(RESUME4);
        assertEquals(RESUME4, storage.get("uuid4"));
        assertEquals(4, storage.size());
    }

    @Test
    void testExistStorageExceptionWhenSave() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME2), "If resume exists should thrown");
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
        assertEquals(RESUME1, storage.get("uuid1"));
    }

    @Test
    void testNotExistStorageExceptionWhenGet() {
        assertThrows(NotExistStorageException.class, () -> storage.get("uuid4"));
    }

    @Test
    void testDelete() {
        storage.delete("uuid2");
        List<Resume> expected = Arrays.asList(RESUME1, RESUME3);
        List<Resume> actual = storage.getAllSorted();
        assertEquals(expected, actual);
        assertEquals(2, storage.size());
    }

    @Test
    void testNotExistStorageExceptionWhenDelete() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("uuid4"));
    }

    @Test
    void getAllSorted() {
        List<Resume> expected = Arrays.asList(RESUME1, RESUME2, RESUME3);
        List<Resume> actual = storage.getAllSorted();
        Collections.sort(actual);
        Collections.sort(expected);
        assertEquals(expected, actual, "Testing method 'getAll'");
    }
}