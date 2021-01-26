import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }


    void delete(String uuid) {
//        int number = 0;
//        for (int i = 0; i < size; i++) {
//            if (storage[i].uuid == uuid) {
//                number = i;
//                break;
//            }
//        }
//
//        while (size - 1 != number) {
//            storage[number] = storage[number + 1];
//            number++;
//        }
//        storage[number] = null;
//        size--;
//        String temp = null;
//        int number = 0;
//        int i = 0;
//        do{
//            for (i = 0; i < size ; i++) {
//                temp = storage[i].uuid;
//            }
//
//        }while(uuid != temp);
//        number = i;
//        while (size - 1 != number) {
//          storage[number] = storage[number + 1];
//           number++;
//       }
//        size--;
        int number = 0;
        boolean found = false;

        while (number < size) {
            if (storage[number].uuid == uuid) {
                found = true;
            }
            if (found) {
                storage[number] = storage[number + 1];

            }
            number++;
        }
        if(found) {
            size--;
        }

    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }


}
