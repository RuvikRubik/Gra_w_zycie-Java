package Organizmy.Rośliny;

import Organizmy.Organizm;
import Organizmy.Roslina;
import Organizmy.Swiat;
import Organizmy.Zwięrzeta.Antylopa;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego(int x, int y, Swiat swiat)
    {
        super(x, y, 10, swiat);
    }

    public void Kolizja(Organizm stojacy,Organizm wchodzacy,int x,int y){
        Vector<Organizm> mapa = wchodzacy.getSwiat().getOrganizmy();
        Informacja(wchodzacy +" zjadł " + stojacy+" po czym umarl"+" ("+getX()+", "+getY()+")");
        wchodzacy.setDousuniecia(true);
        stojacy.setDousuniecia(true);
    }

    public void Akcja() {
        int barszczx = getX();
        int barszczy = getY();
        Vector<Organizm> mapa = getSwiat().getOrganizmy();
        Iterator<Organizm> iterator = mapa.iterator();
        while (iterator.hasNext()) {
            Organizm org = iterator.next();
            if ((org.getX() == barszczx - 1 && org.getY() == barszczy) || (org.getX() == barszczx + 1 && org.getY() == barszczy) || (org.getX() == barszczx && org.getY() == barszczy - 1) || (org.getX() == barszczx && org.getY() == barszczy + 1)) {
                Informacja(this + " zabił " + org+" ("+org.getX()+", "+org.getY()+")");
                org.setDousuniecia(true);
            }
        }
    }

    public String toString(){
        return "Barszcz_Sosnowskiego";
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new BarszczSosnowskiego(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
    public Color Kolor(){
        return new Color(120,80,155);
    }
}
