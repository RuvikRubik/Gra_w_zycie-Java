package Organizmy.Rośliny;

import Organizmy.Organizm;
import Organizmy.Roslina;
import Organizmy.Swiat;
import Organizmy.Zwięrzeta.Antylopa;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class WilczeJagody extends Roslina {
    public WilczeJagody(int x, int y, Swiat swiat)
    {
        super(x, y, 99, swiat);
    }

    public void Kolizja(Organizm stojacy, Organizm wchodzacy, int x, int y){
        Informacja(wchodzacy +" zjadł " + stojacy+" po czym umarl"+" ("+getX()+", "+getY()+")");
        wchodzacy.setDousuniecia(true);
        stojacy.setDousuniecia(true);
    }

    public String toString(){
        return "Wilcze_Jagody";
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new WilczeJagody(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
    public Color Kolor(){
        return new Color(80,155,123);
    }
}
