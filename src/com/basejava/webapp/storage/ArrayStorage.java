package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        String[] temp = new String[size];
        for (int i = 0; i < size; i++) {
            temp[i] = storage[i].getUuid();
        }
        Arrays.sort(temp);
        int indexFound = Arrays.binarySearch(temp, r.getUuid());
        if (indexFound >= 0) {
            storage[indexFound] = r;
        } else {
            System.out.println("This resume does not exist");
        }
    }

    public void save(Resume r) {
        int index;
        storage[size] = r;
        if (size > 0) {
            for (index = 0; index < size; index++) {
                if (storage[index].getUuid().equals(storage[size].getUuid())) {
                    System.out.println("This uuid already exist");
                    storage[size] = null;
                    size--;
                }
            }
        }
        if (size < 10000) {
            size++;
        } else {
            System.out.println("There is no more place for resume");
            storage[size] = null;
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int number = 0;
        boolean isEquals = false;
        for (number = 0; number < size; number++) {
            if (storage[number].getUuid().equals(uuid)) {
                for (int j = number; j < size; j++) {
                    storage[j] = storage[j + 1];
                }
                isEquals = true;
                if (isEquals) {
                    size--;
                }
                break;
            }
        }
        if (!isEquals) {
            System.out.println("This resume does not exist");
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
}
