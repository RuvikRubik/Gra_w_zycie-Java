package Organizmy;

import Organizmy.Zwięrzeta.Antylopa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Czlowiek extends Zwierze {
    private boolean umiejetnosc = false;
    private int czasTrwania = 0, czasOdnowienia = 0;
    public Czlowiek(int x,int y,Swiat swiat){
        super(x,y,5,4,swiat);
        setWiek(1);
    }
    public Color Kolor(){
        return Color.BLACK;
    }
    public void Kolizja(Organizm stojacy,Organizm wchodzacy,int x,int y){
        if(umiejetnosc){
            wchodzacy.setX(x);
            wchodzacy.setY(y);
            Informacja("Przy pomocy super umiejetnosci odparles atak"+" ("+getX()+", "+getY()+")");
        }else{
            super.Kolizja(stojacy,wchodzacy,x,y);
        }
    }

    public void RuchCzlowiek(int keyCode) {
        int tempx = getX();
        int tempy = getY();

        switch (keyCode) {
            case KeyEvent.VK_U:
                if(umiejetnosc){
                    Informacja("Umiejetnosc jeszcze trwa zostało " + czasTrwania);
                }else
                if (czasOdnowienia == 0) {
                    Informacja("Uruchiomiono super umiejetnosc");
                    czasTrwania = 5;
                    umiejetnosc = true;
                }
                else {
                    Informacja("Umiejetnosc niezaladowana trzeba poczekac " + czasOdnowienia);
                }
                break;
            case KeyEvent.VK_UP:
                if (getX() - 1 < 0) {
                    Informacja("Nie można wyjść z mapy");
                }
                else {
                    setX(getX() - 1);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (getX() + 1 >= getSwiat().getX()) {
                    Informacja("Nie można wyjść z mapy");
                }
                else {
                    setX(getX() + 1);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (getY() - 1 < 0) {
                    Informacja("Nie można wyjść z mapy");
                }
                else {
                    setY(getY() - 1);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (getY() + 1 >= getSwiat().getY()) {
                    Informacja("Nie można wyjść z mapy");
                }
                else {
                    setY(getY() + 1);
                }
                break;
        }
        //JPanel[][] panels = getSwiat().getPanels();
        //panels[getX()][getY()].setBackground(null);
        //panels[newRow][newColumn].setBackground(Kolor());
    }

    public void Akcja(){
        int tempx = getX();
        int tempy = getY();
        RuchCzlowiek(getSwiat().getKeyCode());
        CzyPoleZajete(tempx,tempy,this);
        czasTrwania--;
        if (czasTrwania == 0) {
            Informacja("super umiejetnosc wygasla");
            czasOdnowienia = 5;
        }
    }

    public String toString(){
        return "Czlowiek";
    }
    public Organizm Tworz(int x, int y) {
        Organizm temp = new Czlowiek(x, y, getSwiat());
        temp.setWiek(0);
        temp.Rysowanie();
        return temp;
    }
    public String dane(){
        return czasOdnowienia+" "+czasTrwania+" "+umiejetnosc;
    }

    public boolean isUmiejetnosc() {
        return umiejetnosc;
    }

    public void setUmiejetnosc(boolean umiejetnosc) {
        this.umiejetnosc = umiejetnosc;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public int getCzasOdnowienia() {
        return czasOdnowienia;
    }

    public void setCzasOdnowienia(int czasOdnowienia) {
        this.czasOdnowienia = czasOdnowienia;
    }
}
