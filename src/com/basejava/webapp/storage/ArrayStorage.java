package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int number = findEqual(resume.getUuid());
        if (number >= 0) {
            storage[number] = resume;
            System.out.println("Resume " + resume.getUuid() + " was updated");
        } else {
            System.out.println("Resume " + resume.getUuid() + " does not exist");
        }
    }

    public void save(Resume resume) {
        int number = findEqual(resume.getUuid());
        if (number == -1 && size < storage.length) {
            storage[size] = resume;
            size++;
        } else if (number >= 0) {
            System.out.println("This uuid already exist");
        } else if (size >= storage.length) {
            System.out.println("Storage is full");
        }

    }

    public Resume get(String uuid) {
        int number = findEqual(uuid);
        if (number >= 0) {
            return storage[number];
        }
        System.out.println("Resume " + uuid + " does not exist");
        return null;
    }

    public void delete(String uuid) {
        int number = findEqual(uuid);
        if (number == -1) {
            System.out.println("Resume " + uuid + " does not exist");
        } else {
            storage[number] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findEqual(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
