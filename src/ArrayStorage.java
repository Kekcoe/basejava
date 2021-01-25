import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int index;

    void clear() {
        Arrays.fill(storage, 0, size() + 1, 0);
    }

    void save(Resume r) {
        storage[index] = r;
    }

    Resume get(String uuid) {
        int i;
        for (i = 0; i < size(); i++) {
            if (storage[i].uuid == uuid) {
                break;
            }
        }

        return storage[i];
    }

    void delete(String uuid) {
        int sizeArr = size();
        int number = 0;
        for (int i = 0; i < sizeArr; i++) {
            if (storage[i].uuid == uuid) {
                number = i;
                break;
            }
        }

        while (sizeArr - 1 != number) {
            storage[number] = storage[number + 1];
            number++;
        }
        storage[number] = null;
        sizeArr--;
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size++;
            }
        }
        return size;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
