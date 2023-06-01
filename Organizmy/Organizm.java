package Organizmy;

import Organizmy.Zwięrzeta.Lis;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Organizm implements Comparable<Organizm>{
    private int sila,inicjatywa,x,y,wiek;
    private boolean dousuniecia = false;
    private Swiat swiat;
    public Organizm(){

    }
    public Organizm(int x,int y,int sila, Swiat swiat){
        this.x = x;
        this.y = y;
        this.sila = sila;
        this.swiat = swiat;
    }
    public void Akcja(){}
    public void Kolizja(Organizm stojacy,Organizm wchodzacy,int x,int y){
        Vector<Organizm> mapa = wchodzacy.getSwiat().getOrganizmy();
        if(stojacy.sila > wchodzacy.sila){
            Informacja(stojacy +" zjadł " + wchodzacy+" ("+getX()+", "+getY()+")");
            wchodzacy.dousuniecia = true;
            stojacy.Rysuj(getX(),getY());
        }else{
            Informacja(wchodzacy +" zjadł " + stojacy+" ("+getX()+", "+getY()+")");
            stojacy.dousuniecia = true;
            wchodzacy.Rysuj(x,y);
        }
    }
    public void Rysowanie(){
        JPanel[][] temp = getSwiat().getPanels();
        temp[getX()][getY()].setBackground(Kolor());
    }
    public void Rysowanie(int x,int y){
        JPanel[][] temp = getSwiat().getPanels();
        temp[x][y].setBackground(Kolor());
    }
    public String toString(){
        return "";
    }
    public Boolean CzyRoslina(){
        return true;
    }
    public void Informacja(String informacja)
    {
        swiat.setInformacja(informacja);
        //System.out.println(informacja);
    }
    public Organizm Szukaj(Vector<Organizm> organizmy,int x,int y,Organizm szukany){
        for(Organizm org: organizmy){
            if(org.x == x && org.y ==y && org != szukany){
                return org;
            }
        }
        return null;
    }
    public void CzyPoleZajete(int x,int y,Organizm org){
        Organizm temp = Szukaj(this.getSwiat().getOrganizmy(),x,y,org);
        if(temp != null){
            temp.Kolizja(temp,org,x,y);
        }else{
            org.Rysuj(x,y);
        }
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public void setInicjatywa(int inicjatywa) {
        this.inicjatywa = inicjatywa;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public Swiat getSwiat() {
        return swiat;
    }

    public void setSwiat(Swiat swiat) {
        this.swiat = swiat;
    }

    @Override
    public int compareTo(Organizm o) {
        if(this.getInicjatywa() == o.getInicjatywa()){
            if(this.getWiek() > o.getWiek()){
                return 1;
            }else{
                return -1;
            }
        }
        if(this.getInicjatywa() > o.getInicjatywa()){
             return 1;
        }else{
            return -1;
        }
    }

    public void Rysuj(int poprzedniX,int poprzedniY){}
    public Color Kolor(){
        return Color.GRAY;
    }

    public Organizm Tworz(int x, int y) {return null;}

    public boolean isDousuniecia() {
        return dousuniecia;
    }

    public void setDousuniecia(boolean dousuniecia) {
        this.dousuniecia = dousuniecia;
    }
}
