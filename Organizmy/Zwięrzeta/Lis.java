package Organizmy.Zwięrzeta;

import Organizmy.Organizm;
import Organizmy.Swiat;
import Organizmy.Zwierze;

import java.awt.*;

public class Lis extends Zwierze {
    public Lis(int x, int y, Swiat swiat)
    {
        super(x, y, 3,7, swiat);
    }
    public void Akcja() {
        Organizm temp;
        int tempx = getX(), tempy = getY();
        while (true) {
            Ruch(1);
            temp = Szukaj(getSwiat().getOrganizmy(), getX(), getY(), this);
            if (temp != null) {
                if (temp.getSila() > getSila()) {
                    setX(tempx);
                    setY(tempy);
                }
                else {
                    if (temp != null) {
                        temp.Kolizja(temp, this, getX(), getY());
                    }
                    else {
                        Rysuj(tempx,tempy);
                    }
                    return;
                }
            }
            else {
                Rysuj(tempx,tempy);
                return;
            }
        }
    }
    public String toString(){
        return "Lis";
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Lis(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
    public Color Kolor(){
        return Color.ORANGE;
    }
}
