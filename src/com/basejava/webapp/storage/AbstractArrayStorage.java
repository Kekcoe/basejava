package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    public void updateResume(Resume resume, int index) {
        storage[index] = resume;
        System.out.println("Resume " + resume.getUuid() + " was updated");
    }

    @Override
    public void saveResumeToStorage(Resume resume, int index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addResumeToArrayStorage(resume, index);
        size++;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void removeResumeFromStorage(String uuid) {
        deleteResumeFromArrayStorage(uuid, getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected Resume getResumeFromStorage(String uuid, int index) {
        return storage[index];
    }

    protected abstract void addResumeToArrayStorage(Resume resume, int index);

    protected abstract void deleteResumeFromArrayStorage(String uuid, int index);

}