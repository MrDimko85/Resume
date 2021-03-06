package com.company.storage;

import com.company.model.Resume;

import java.util.Arrays;

public class ArrayStorage implements Storage{
    private static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear (){
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if(index != -1) {
            System.out.println("Resume" + r.getUuid() + "already exist");
        }
        else if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        }
        else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if(index == -1) {
            System.out.println("Resume" + r.getUuid() + "not exist");
        }
        else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("Resume" + uuid + "not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println(("ERROR"));
        }
        else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }


   public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
