package Organizmy.Rośliny;

import Organizmy.Organizm;
import Organizmy.Roslina;
import Organizmy.Swiat;
import Organizmy.Zwięrzeta.Antylopa;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class Trawa extends Roslina {
    public Trawa(int x, int y, Swiat swiat)
    {
        super(x, y, 0, swiat);
    }

    public String toString(){
        return "Trawa";
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Trawa(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
    public Color Kolor(){
        return new Color(36,135,21);
    }
}
