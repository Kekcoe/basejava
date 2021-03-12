package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.LinkedList;

public class ListStorage extends AbstractStorage {
    private final LinkedList<Resume> listStorage = new LinkedList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void saveResumeToStorageMain(Resume resume) {
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
    public void deleteResumeFromStorageMain(String uuid) {
        listStorage.remove(new Resume(uuid));
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

    @Override
    protected int getIndex(String uuid) {
        return listStorage.indexOf(new Resume(uuid));
    }

    private boolean isResumeExist(Resume resume) {
        return listStorage.contains(resume);
    }

    protected void updateResume(Resume resume) {
        listStorage.set(listStorage.indexOf(resume), resume);
        System.out.println("Resume " + resume.getUuid() + " was updated");
    }

    @Override
    protected Resume getResumeFromStorage(String uuid) {
        return listStorage.get(getIndex(uuid));
    }
}
