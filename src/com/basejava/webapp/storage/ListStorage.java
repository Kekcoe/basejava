package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.LinkedList;

public class ListStorage extends AbstractStorage {
    private final LinkedList<Resume> listStorage = new LinkedList<Resume>();


    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void update(Resume resume) {
        if (!isResumeExist(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        listStorage.set(listStorage.indexOf(resume), resume);
        System.out.println("Resume " + resume.getUuid() + " was updated");
    }

    @Override
    public void save(Resume resume) {
        if (isResumeExist(resume)) {
            throw new ExistStorageException(resume.getUuid());
        }
        listStorage.add(resume);
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        if (!isResumeExist(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        return listStorage.get(listStorage.indexOf(resume));
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        if (!isResumeExist(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        listStorage.remove(resume);
    }

    @Override
    public Resume[] getAll() {
        Resume[] listStorageToArray = new Resume[listStorage.size()];
                listStorageToArray = listStorage.toArray(listStorageToArray);
        return listStorageToArray;
    }

    @Override
    public int size() {
        return listStorage.size();
    }


    private boolean isResumeExist(Resume resume) {
        return listStorage.contains(resume);
    }
}
