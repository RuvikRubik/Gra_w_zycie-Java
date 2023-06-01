package Organizmy.Rośliny;

import Organizmy.Organizm;
import Organizmy.Roslina;
import Organizmy.Swiat;
import Organizmy.Zwięrzeta.Antylopa;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class Mlecz extends Roslina {
    public Mlecz(int x, int y, Swiat swiat)
    {
        super(x, y, 0, swiat);
    }
    public void Akcja() {
        Rozprzeszenianie();
        Rozprzeszenianie();
        Rozprzeszenianie();
    }

    public String toString(){
        return "Mlecz";
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Mlecz(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
    public Color Kolor(){
        return new Color(120,80,155);
    }
}
