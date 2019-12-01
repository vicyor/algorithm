package com.vicyor.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
   猫狗队列
   1.add        入队列
   2.pollAll    先进先出
   3.pollDog    Dog先进先出
   4.pollCat    Cat先进先出
   5.isEmpty
   6.isDogEmpty
   7.isCatEmpty
 */
public class Question_4 {

}

class Pet {
    private String type;

    Pet(String type) {
        this.type = type;
    }

    public String getPetType() {
        return this.type;
    }
}

class Dog extends Pet {

    public Dog(String type) {
        super("dog");
    }
}

class Cat extends Pet {

    public Cat(String type) {
        super("cat");
    }
}

//我的解
class Question_4_MySlove {
    private List<Pet> pets = new ArrayList<>();

    public void add(Pet pet) {
        pets.add(pet);
    }

    public List<Pet> pollAll() {
        List<Pet> tmp = pets;
        pets = new ArrayList<>();
        return tmp;
    }

    public List<Pet> pollDog() {
        List<Pet> dogs = pets.stream().filter(pet -> pet.getPetType().equals("dog")).collect(Collectors.toList());
        pets.removeAll(dogs);
        return dogs;
    }

    public boolean isEmpty() {
        return pets.isEmpty();
    }

    public boolean isDogEmpty() {
        long count = pets.stream().filter(pet -> pet.getPetType().equals("dog"))
                .count();
        return count == 0;
    }
}