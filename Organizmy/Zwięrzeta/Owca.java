package Organizmy.Zwięrzeta;

import Organizmy.Organizm;
import Organizmy.Swiat;
import Organizmy.Zwierze;

import java.awt.*;

public class Owca extends Zwierze {
    public Owca(int x, int y, Swiat swiat)
    {
        super(x, y, 4,4, swiat);
    }
    public String toString(){
        return "Owca";
    }
    public Color Kolor(){
        return Color.white;
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Owca(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
}
