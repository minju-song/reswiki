package com.teddybear.reswiki.practice;

public class practice {
    public static void main(String[] args) {
//        animal();
        lamda();
    }

    static void animal() {
        Animal.parent();
        Animal dog = Animal.of("dog");

        dog.sound();

        Animal cat = Animal.of("cat");
        cat.sound();

        Cat cat2 = new Cat();
        cat2.sound();
    }

    static void lamda() {
        MyFunctionalInterface myFunc = (msg) -> System.out.println(msg);
//        MyFunctionalInterface calculate = String::length;
//        int length = calculate.leng("Hid");
//        System.out.println(length);
        Animal a = Animal.of("cat");
        myFunc.leng(a);

    }

}

