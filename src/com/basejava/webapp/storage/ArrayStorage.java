package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void addResumeToArrayStorage(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteResumeFromArrayStorage(String uuid, int index) {
        storage[index] = storage[size - 1];
    }
}
