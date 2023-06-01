package Organizmy.Zwięrzeta;

import Organizmy.Organizm;
import Organizmy.Swiat;
import Organizmy.Zwierze;

import java.awt.*;
import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw(int x, int y, Swiat swiat)
    {
        super(x, y, 2,1, swiat);
    }
    public void Kolizja(Organizm stojacy, Organizm wchodzacy, int x, int y){
        if (stojacy.getClass() == wchodzacy.getClass()) {
            if(y + 1 <= stojacy.getSwiat().getY()){
                Organizm temp = Szukaj(getSwiat().getOrganizmy(),getX(),getY()+1,null);
                if(temp == null){
                    Informacja("Narodziny " + wchodzacy+" ("+getX()+", "+getY()+1+")");
                    this.getSwiat().addNoweOrganizmy(this.Tworz(getX(),getY()+1));
                    wchodzacy.setX(x);
                    wchodzacy.setY(y);
                }
            }
        }
	else {
            if (wchodzacy.getSila() < 5) {
                Informacja(stojacy + " odparł " + wchodzacy+" ("+getX()+", "+getY()+")");
                wchodzacy.setX(x);
                wchodzacy.setY(y);
                wchodzacy.Rysowanie(x,y);
            }
            else {
                super.Kolizja(stojacy, wchodzacy, x, y);
            }
        }
    }
    public void Akcja() {
        Random random = new Random();
        int ruch = 1 + random.nextInt(4);
        int tempx = getX(), tempy = getY();
        if (ruch == 1) {
            Ruch(1);
            //Rysuj(tempx,tempy);
            CzyPoleZajete(tempx, tempy, this);
        }
    }
    public String toString(){
        return "Zolw";
    }
    public Color Kolor(){
        return Color.green;
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Zolw(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
}
