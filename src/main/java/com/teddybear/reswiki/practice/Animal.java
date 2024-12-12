package com.teddybear.reswiki.practice;

public interface Animal {

    void sound();

    static void parent() {
        System.out.println("동물 종류임");
    }

    static Animal of(String s) {
        switch (s) {
            case "dog" :
                return new Dog();
            case "cat" :
                return new Cat();
            default:
                throw new IllegalArgumentException(s+" 타입은 없습니다.");
        }
    }
}
