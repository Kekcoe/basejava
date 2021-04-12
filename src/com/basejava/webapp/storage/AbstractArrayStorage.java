package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    public void updateResume(Resume resume, Object index) {
        storage[(Integer) index] = resume;
        System.out.println("Resume " + resume.getUuid() + " was updated");
    }

    @Override
    public void saveResume(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addResumeToArrayStorage(resume, (Integer) index);
        size++;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void removeResume(Object index) {
        deleteResumeFromArrayStorage((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void addResumeToArrayStorage(Resume resume, int index);

    protected abstract void deleteResumeFromArrayStorage(int index);

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}