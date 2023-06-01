package Organizmy;

import Organizmy.Organizm;
import java.util.Random;
import java.util.Vector;

public class Roslina extends Organizm {
    public Roslina(){}
    public Roslina(int x,int y,int sila,Swiat swiat){
        super(x,y,sila,swiat);
        setInicjatywa(0);
    }
    public void Kolizja(Organizm stojacy,Organizm wchodzacy,int x,int y){
        Vector<Organizm> mapa = wchodzacy.getSwiat().getOrganizmy();
        Informacja(wchodzacy +" zjadł " + stojacy+" ("+getX()+", "+getY()+1+")");
        wchodzacy.Rysuj(x,y);
        stojacy.setDousuniecia(true);
    }
    public void Rozprzeszenianie(){
        Random random = new Random();
        int pole = -1 + random.nextInt(3);
        if(pole != 0){
            if(getX() + pole >= 0 && getX() + pole < getSwiat().getX() && getY() + pole >= 0 && getY() + pole < getSwiat().getY()){
                Organizm temp = Szukaj(getSwiat().getOrganizmy(),getX()+pole,getY()+pole,null);
                if(temp == null){
                    int tempx = getX()+pole;
                    int tempy = getY()+pole;
                    Informacja("Rozsiano nowe "+this +" ("+tempx+", "+tempy+")");
                    this.getSwiat().addNoweOrganizmy(this.Tworz(tempx,tempy));
                }
            }
        }
    }
    public Boolean CzyRoslina(){
        return true;
    }
    public void Akcja(){
        Rozprzeszenianie();
        setWiek(getWiek()+1);
    }
}
