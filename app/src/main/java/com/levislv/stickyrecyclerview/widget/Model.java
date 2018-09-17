package com.levislv.stickyrecyclerview.widget;

public class Model {
    private String letter;
    private String name;

    public Model() {

    }

    public Model(String letter, String name) {
        this.letter = letter;
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Model{" +
                "letter='" + letter + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
