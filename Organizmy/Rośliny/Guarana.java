package Organizmy.Rośliny;

import Organizmy.Organizm;
import Organizmy.Roslina;
import Organizmy.Swiat;
import Organizmy.Zwięrzeta.Antylopa;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class Guarana extends Roslina {
    public Guarana(int x, int y, Swiat swiat)
    {
        super(x, y, 0, swiat);
    }

    public void Kolizja(Organizm stojacy, Organizm wchodzacy, int x, int y){
        Informacja(wchodzacy +" zjadł " + stojacy+" i otrzymal 3 do sily"+" ("+getX()+", "+getY()+1+")");
        wchodzacy.setSila(wchodzacy.getSila()+3);
        stojacy.setDousuniecia(true);
    }

    public String toString(){
        return "Guarana";
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Guarana(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
    public Color Kolor(){
        return Color.PINK;
    }
}
