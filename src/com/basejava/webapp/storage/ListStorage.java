package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listStorage = new LinkedList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public void saveResumeToStorage(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    public void removeResumeFromStorage(String uuid, int index) {
        listStorage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[listStorage.size()];
        return listStorage.toArray(resumes);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return listStorage.indexOf(new Resume(uuid));
    }

    protected void updateResume(Resume resume, int index) {
        listStorage.set(index, resume);
        System.out.println("Resume " + resume.getUuid() + " was updated");
    }

    @Override
    protected Resume getResumeFromStorage(String uuid, int index) {
        return listStorage.get(index);
    }
}
