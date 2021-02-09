package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0 ) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
             index = -(index) - 1;
            System.arraycopy(storage,index, storage,index +1, size - index);
            storage[index] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + "does not exist");
        } else {
            System.arraycopy(storage,index + 1, storage, index, size - index);
            storage[size - 1] = null;
            size --;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage,0,size,searchKey);
    }
}