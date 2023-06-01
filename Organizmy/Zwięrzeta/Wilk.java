package Organizmy.Zwięrzeta;

import Organizmy.Organizm;
import Organizmy.Swiat;
import Organizmy.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk(int x, int y, Swiat swiat)
    {
        super(x, y, 9,5, swiat);
    }
    public String toString(){
        return "Wilk";
    }
    public Color Kolor(){
        return Color.RED;
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Wilk(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
}
