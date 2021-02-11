package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void resumeAddToStorage(Resume resume, int index) {
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
        size++;
    }

    @Override
    protected void resumeDeleteFromStorage(String uuid, int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}
