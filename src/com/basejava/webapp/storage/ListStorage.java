package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void saveResume(Resume resume, Object searchKey) {
        listStorage.add(resume);
    }

    @Override
    public void removeResume(Object searchKey) {
        listStorage.remove(((Integer) searchKey).intValue());
    }

    @Override
    public List<Resume> getResumes() {
        return listStorage;
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    protected void updateResume(Resume resume, Object searchKey) {
        listStorage.set((Integer) searchKey, resume);
        System.out.println("Resume " + resume.getUuid() + " was updated");
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return listStorage.get((Integer) searchKey);
    }
}