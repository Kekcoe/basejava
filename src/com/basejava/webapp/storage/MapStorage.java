package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> mapStorage = new LinkedHashMap<>();

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        Set<String> keys = mapStorage.keySet();
        List<String> listKeys = new ArrayList<>(keys);
        for (int i = 0; i < listKeys.size(); i++) {
            if (uuid == listKeys.get(i)) {
                return i;
            }
        }
        return listKeys.indexOf(resume);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
        System.out.println("Resume " + resume.getUuid() + " was updated");
    }

    @Override
    protected void saveResumeToStorage(Resume resume, int index) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResumeFromStorage(String uuid, int index) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void removeResumeFromStorage(String uuid, int index) {
        mapStorage.remove(uuid);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        List<Resume> listResume = new ArrayList<>(mapStorage.values());
        Resume[] arrResume = new Resume[listResume.size()];
        return listResume.toArray(arrResume);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}