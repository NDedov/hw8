package hw8;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;

class Item{
    private final int data;

    public Item(int data){
        this.data = data;
    }

    public int getKey(){
        return this.data;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}

class HashTable {
    private final LinkedList<Item>[] hashArr;
    private final int arrSize;

    public HashTable(int size){
        this.arrSize = size;
        hashArr = new LinkedList[arrSize];
        for (int i = 0; i < size; i++) {
            hashArr[i] = new LinkedList<>();
        }
    }

    public void display(){
        for(int i=0;i<arrSize;i++)
            System.out.printf("%s ", Arrays.toString(hashArr[i].toArray()));
        System.out.println();



    }

    public int hashFunc(int key){
        return key % arrSize;
    }

    /**
     * Вставка элемента в хэш-таблицу. Возникающие коллизии решаются путем добавления нового элемента
     * списка по тому же индексу хэш-таблицы
     * @param item элемент
     */
    public void insert(Item item){
        int key = item.getKey();
        int hashVal = hashFunc(key);
        hashArr[hashVal].add(item);
    }

    /**
     * Удаление элемента в хэш-таблице. Находим нужный элемент в списке и удаляем
     * @param key значение элемента
     * @return истина если нашли и удалили, ложь - если не нашли
     */
    public boolean delete(int key){
        int hashVal = hashFunc(key);
        for (int i = 0; i < hashArr[hashVal].size(); i++)
            if (hashArr[hashVal].get(i).getKey() == key){
                hashArr[hashVal].remove(i);
                return true;
            }
        return false;
    }

    /**
     * Поиск элемента по значению в хэш-таблице
     * @param key значение элемента
     * @return возращает объект с искомым значением ключа.
     */
    public Item find(int key){
        int hashVal = hashFunc(key);
        for (int i = 0; i < hashArr[hashVal].size(); i++)
            if (hashArr[hashVal].get(i).getKey() == key)
                return hashArr[hashVal].get(i);
        return null;
    }
}

public class Main {
    public static void main(String[] args){
        int size = 25;
        HashTable hashTable = new HashTable(size);

        hashTable.insert(new Item(10));
        hashTable.insert(new Item(20));
        hashTable.insert(new Item(30));
        hashTable.insert(new Item(75));
        hashTable.insert(new Item(40));
        hashTable.insert(new Item(50));
        hashTable.insert(new Item(60));
        hashTable.insert(new Item(70));
        hashTable.insert(new Item(100));
        hashTable.insert(new Item(110));

        hashTable.display();
        hashTable.delete(50);
        hashTable.delete(110);
        hashTable.delete(30);
        hashTable.display();

        System.out.println(hashTable.find(70));
        System.out.println(hashTable.find(125));

        String[] arr1 = {"1","2","3"};
        String[] arr2 = {"4","5","6"};
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        swapPlaces(arr1, arr2, 0);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));



    }
    public static <T> void swapPlaces(T[] arr1, T[] arr2, int number){
        T element = arr1[number];
        arr1[number] = arr2[number];
        arr2[number] = element;
    }
}


