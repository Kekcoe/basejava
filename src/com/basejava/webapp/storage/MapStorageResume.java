package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageResume extends AbstractStorage {
    private final Map<String, Resume> mapStorageResume = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return mapStorageResume.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        mapStorageResume.put(resume.getFullName(), resume);
        System.out.println("Resume " + resume.getUuid() + " was updated");
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        mapStorageResume.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void removeResume(Object searchKey) {
        mapStorageResume.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        mapStorageResume.clear();
    }

    @Override
    public List<Resume> getResumes() {
        return new ArrayList<>(mapStorageResume.values());
    }

    @Override
    public int size() {
        return mapStorageResume.size();
    }
}