package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {


    public abstract void clear();

    public  void update(Resume resume){
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateResume(resume);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResumeToStorageMain(resume);
    }

     public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResumeFromStorage(uuid);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResumeFromStorageMain(uuid);
    }

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume resume);

    protected abstract void saveResumeToStorageMain(Resume resume);

    protected abstract Resume getResumeFromStorage(String uuid);

    protected abstract void deleteResumeFromStorageMain(String uuid);

}
