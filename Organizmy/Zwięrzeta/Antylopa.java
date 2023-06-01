package Organizmy.Zwięrzeta;

import Organizmy.Organizm;
import Organizmy.Swiat;
import Organizmy.Zwierze;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class Antylopa extends Zwierze {

    public Antylopa(int x, int y, Swiat swiat)
    {
        super(x, y, 4,4, swiat);
    }

    public void Kolizja(Organizm stojacy, Organizm wchodzacy, int x, int y){
        KolizjaAntylopa(stojacy,wchodzacy,x,y);
    }

    public void KolizjaAntylopa(Organizm stojacy, Organizm wchodzacy, int x, int y){
        if (!stojacy.CzyRoslina()) {
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
                Random random = new Random();
                int ruch = 1 + random.nextInt(2);
                if (ruch == 1) {
                    stojacy.Kolizja(stojacy, wchodzacy, x, y);
                }
                else {
                    Informacja(this + " Uciekl"+" ("+getX()+", "+getY()+")");
                    if (stojacy instanceof Antylopa) {
                        stojacy.setX(x);
                        stojacy.setY(y);
                    }
                else {
                        wchodzacy.setX(x);
                        wchodzacy.setY(y);
                    }
                }
            }
        }
        else {
            stojacy.Kolizja(stojacy, wchodzacy, x, y);
        }
    }
    public void Akcja() {
        int tempx = getX();
        int tempy = getY();
        Ruch(2);
        setWiek(getWiek()+1);
        Organizm temp = Szukaj(this.getSwiat().getOrganizmy(),getX(),getY(),this);
        if(temp != null){
            KolizjaAntylopa(temp,this,tempx,tempy);
        }else{
            Rysuj(tempx,tempy);
        }
    }

    public String toString(){
        return "Antylopa";
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Antylopa(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
    public Color Kolor(){
        return Color.MAGENTA;
    }
}
