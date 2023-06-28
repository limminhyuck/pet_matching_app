package com.example.firebase;

public class animal {
    String name;
    String age;
    String species;
    String location;
    String sex;
    String breed;

    public animal(){}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAge(){
        return age;
    }

    public void setAge(String age){
        this.age = age;
    }

    public String getSpecies(){
        return species;
    }

    public void setSpecies(String species){
        this.species = species;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public String getBreed(){
        return breed;
    }

    public void setBreed(String breed){
        this.breed = breed;
    }

    public animal(String name, String age, String species, String location, String sex, String breed){
        this.name= name;
        this.age = age;
        this.species = species;
        this.location = location;
        this.sex = sex;
        this.breed = breed;
    }

}
