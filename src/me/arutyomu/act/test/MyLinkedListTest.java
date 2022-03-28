package me.arutyomu.act.test;

import me.arutyomu.act.util.MyLinkedList;
import me.arutyomu.act.util.MyList;

public class MyLinkedListTest {

    public static void main(String[] args) {

        // Create of my linked list implementation
        MyList<Integer> list = new MyLinkedList<>();

        // Add some objects to list
        list.add(1);
        list.add(2);
        list.add(3);

        // Add object via id
        list.add(0, 7);
        list.add(2, 8);
        list.add(list.size() - 1, 9);

        // Clear of list


        // Print array
        System.out.println(list);

    }

}
