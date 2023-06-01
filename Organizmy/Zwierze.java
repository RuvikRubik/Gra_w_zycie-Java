package Organizmy;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class Zwierze extends Organizm {


    public Zwierze(){}
    public Zwierze(int x,int y,int inicjatywa,int sila,Swiat swiat){
        super(x,y,sila,swiat);
        setInicjatywa(inicjatywa);
    }
    public void Kolizja(Organizm stojacy,Organizm wchodzacy,int x,int y){
        if(stojacy.getClass() == wchodzacy.getClass()){
            if(y + 1 < stojacy.getSwiat().getY()){
                Organizm temp = Szukaj(getSwiat().getOrganizmy(),getX(),getY()+1,null);
                if(temp == null){
                    int tempy = getY()+1;
                    Informacja("Narodziny " + wchodzacy+" ("+getX()+", "+tempy+")");
                    this.getSwiat().addNoweOrganizmy(this.Tworz(getX(),getY()+1));
                    wchodzacy.setX(x);
                    wchodzacy.setY(y);
                }
            }
        }else{
            super.Kolizja(stojacy,wchodzacy,x,y);
        }
    }
    public void Ruch(int liczba){
        Random random = new Random();
        int dobryruch = 0;
        while (dobryruch == 0){
            int ruch = 1 + random.nextInt(4);
            switch (ruch){
                case 1:{
                    if(getY() - liczba >= 0){
                        setY(getY()-liczba);
                        dobryruch = 1;
                    }
                    break;
                }
                case 2:{
                    if(getY() + liczba < getSwiat().getY()){
                        setY(getY()+liczba);
                        dobryruch = 1;
                    }
                    break;
                }
                case 3:{
                    if(getX() - liczba >= 0){
                        setX(getX()-liczba);
                        dobryruch = 1;
                    }
                    break;
                }
                case 4:{
                    if(getX() + liczba < getSwiat().getX()){
                        setX(getX()+liczba);
                        dobryruch = 1;
                    }
                    break;
                }
            }
        }
    }
    public Boolean CzyRoslina(){
        return false;
    }
    public void Akcja(){
        int tempx = getX();
        int tempy = getY();
        Ruch(1);
        setWiek(getWiek()+1);
        //Rysuj(tempx,tempy);
        CzyPoleZajete(tempx,tempy,this);
    }

    public void Rysuj(int poprzedniX,int poprzedniY){
        JPanel[][] temp = getSwiat().getPanels();
        temp[poprzedniX][poprzedniY].setBackground(Color.GRAY);
        temp[getX()][getY()].setBackground(Kolor());
    }
}
