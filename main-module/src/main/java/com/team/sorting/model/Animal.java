package com.team.sorting.model;

public class Animal {

    public enum Species {
        DOG, CAT, HORSE, RABBIT
    }

    public enum EyeColor {
        BROWN, BLUE, GREEN, YELLOW
    }

    public enum Fur {
        SHORT, LONG, CURLY, NONE
    }

    private Species species;
    private EyeColor eyeColor;
    private Fur fur;
    private boolean eatsBun;

    public Species getSpecies() {
        return species;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public Fur getFur() {
        return fur;
    }

    public boolean isEatsBun() {
        return eatsBun;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setFur(Fur fur) {
        this.fur = fur;
    }

    public void setEatsBun(boolean eatsBun) {
        this.eatsBun = eatsBun;
    }

    @Override
    public String toString() {
        return "Животное: " +
                (species != null ? species + ", " : "") +
                (eyeColor != null ? "глаза " + eyeColor + ", " : "") +
                (fur != null ? "шерсть " + fur + ", " : "") +
                (eatsBun ? "любит булку, " : "не любит булку, ") +
                "готово к жизни!";
    }
}
