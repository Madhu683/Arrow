package com.example.arrow;

public class CategoryModel {

    private int Levels;
    private String name;
    public CategoryModel()
    {

    }

    public CategoryModel(int levels, String name) {
        Levels = levels;
        this.name = name;
    }

    public int getLevels() {
        return Levels;
    }

    public void setLevels(int levels) {
        Levels = levels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
