package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        checkIndex(uuid, index);
        updateResume(resume, index);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, index);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        checkIndex(uuid, index);
        return getResume(uuid, index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        checkIndex(uuid, index);
        removeResume(uuid, index);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract Resume getResume(String uuid, int index);

    protected abstract void removeResume(String uuid, int index);

    private void checkIndex(String uuid, int index) {
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
    }
}