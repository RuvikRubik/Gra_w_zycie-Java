package Organizmy;

import Organizmy.Rośliny.*;
import Organizmy.Zwięrzeta.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Collections;
public class Swiat extends JFrame{
    private int x,y;
    private List<Organizm> noweOrganizmy = new ArrayList<>();
    private JPanel[][] panels;
    private int keyCode;
    private final Swiat swiat;
    private final JFrame frame;

    private JPanel gridpanel;
    private JFrame newframe;
    private JLabel informacja;
    private Vector<Organizm> Organizmy = new Vector<>();

    public Swiat(){
        JFrame frame = new JFrame("Rozmiar mapy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 80);
        frame.setLayout(new FlowLayout());

        JLabel label1 = new JLabel("Szerokość:");
        JTextField textField1 = new JTextField(10);

        JLabel label2 = new JLabel("Wysokość:");
        JTextField textField2 = new JTextField(10);

        JButton createButton = new JButton("Stwórz");

        this.swiat = this;
        this.frame = frame;
        createButton.addActionListener(new MyActionListener(textField1,textField2));

        frame.add(label1);
        frame.add(textField1);
        frame.add(label2);
        frame.add(textField2);
        frame.add(createButton);

        pack();
        frame.setVisible(true);
    }

    private void dodajPrzyciski(JPanel Grid, int rows, int columns) {
        Grid.removeAll();
        panels = new JPanel[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.gray);
                panel.setBorder(createBorder());
                panel.addMouseListener(new Swiat.PanelClickListener(i, j));
                Grid.add(panel);
                panels[i][j] = panel;
            }
        }
        Grid.revalidate();
        Grid.repaint();
    }

    private void zmienRozmiarGrida(int rows, int columns) {
        gridpanel.setLayout(new GridLayout(rows, columns));
        dodajPrzyciski(gridpanel, rows, columns);
    }
    private class MyActionListener  implements ActionListener {
        private final JTextField textField1;
        private final JTextField textField2;
        public MyActionListener(JTextField textField1,JTextField textField2){
            this.textField1 = textField1;
            this.textField2 = textField2;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            int rows = Integer.parseInt(textField1.getText());
            int columns = Integer.parseInt(textField2.getText());
            JFrame newFrame = new JFrame("Gra w życie");
            newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            newFrame.setSize(1500, 1000);
            newFrame.setLayout(new GridLayout(1, 2));
            JPanel gridPanel = new JPanel();
            gridpanel = gridPanel;
            gridPanel.setLayout(new GridLayout(rows, columns));
            panels = new JPanel[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    JPanel panel = new JPanel();
                    panel.setBackground(Color.gray);
                    panel.setBorder(createBorder());
                    panel.addMouseListener(new Swiat.PanelClickListener(i, j));
                    gridPanel.add(panel);
                    panels[i][j] = panel;
                }
            }
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel label1 = new JLabel();
            JButton Save = new JButton("Save");
            JButton Load = new JButton("Load");
            JButton Tura = new JButton("Tura");
            Save.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                            String linia = x +" "+ y;
                            writer.write(linia);
                            writer.newLine();
                            for(Organizm org: Organizmy){
                                if(org instanceof Czlowiek){
                                    linia = org.getX()+" "+org.getY()+" "+org.getWiek()+" "+org.getSila()+" "+ ((Czlowiek) org).dane()+" "+org;
                                }else{
                                    linia = org.getX()+" "+org.getY()+" "+org.getWiek()+" "+org.getSila()+" "+org;
                                }

                                writer.write(linia);
                                writer.newLine();
                            }
                            writer.flush();
                            setInformacja("Dane zostały zapisane do pliku: " + filePath);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            Load.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                            String line = reader.readLine();
                            String[] rozmiary = line.split(" ");
                            x = Integer.parseInt(rozmiary[0]);
                            y = Integer.parseInt(rozmiary[1]);
                            zmienRozmiarGrida(x,y);
                            Organizm temp = null;
                            clearOrganizmy();
                            while ((line = reader.readLine()) != null) {
                                String[] elementyZwierzat = line.split(" ");
                                int x = Integer.parseInt(elementyZwierzat[0]);
                                int y = Integer.parseInt(elementyZwierzat[1]);
                                switch (elementyZwierzat[4]){
                                    case "Barszcz_Sosnowskiego":{
                                        temp = new BarszczSosnowskiego(x, y, swiat);
                                        break;
                                    }
                                    case "Guarana":{
                                        temp = new Guarana(x, y, swiat);
                                        break;
                                    }
                                    case "Mlecz":{
                                        temp = new Mlecz(x, y, swiat);
                                        break;
                                    }
                                    case "Trawa":{
                                        temp = new Trawa(x, y, swiat);
                                        break;
                                    }
                                    case "Wilcze_Jagody":{
                                        temp = new WilczeJagody(x, y, swiat);
                                        break;
                                    }
                                    case "Antylopa":{
                                        temp = new Antylopa(x, y, swiat);
                                        break;
                                    }
                                    case "Lis":{
                                        temp = new Lis(x, y, swiat);
                                        break;
                                    }
                                    case "Owca":{
                                        temp = new Owca(x, y, swiat);
                                        break;
                                    }
                                    case "Wilk":{
                                        temp = new Wilk(x, y, swiat);
                                        break;
                                    }
                                    case "Zolw":{
                                        temp = new Zolw(x, y, swiat);
                                        break;
                                    }
                                    default:{
                                        Czlowiek temp2 = new Czlowiek(x, y, swiat);
                                        temp2.setWiek(Integer.parseInt(elementyZwierzat[2]));
                                        temp2.setSila(Integer.parseInt(elementyZwierzat[3]));
                                        temp2.setCzasOdnowienia(Integer.parseInt(elementyZwierzat[4]));
                                        temp2.setCzasTrwania(Integer.parseInt(elementyZwierzat[5]));
                                        temp2.setUmiejetnosc(Boolean.parseBoolean(elementyZwierzat[6]));
                                        setOrganizmy(temp2);
                                        break;
                                    }
                                }
                                if (temp != null){
                                    temp.setWiek(Integer.parseInt(elementyZwierzat[2]));
                                    temp.setSila(Integer.parseInt(elementyZwierzat[3]));
                                    setOrganizmy(temp);
                                }
                            }
                            setInformacja("Dane zostały odczytane z pliku: " + filePath);
                            newframe.requestFocusInWindow();
                            Rozgrywka();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            Tura.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    WykonajTure();
                }
            });
            informacja = label1;
            //label1.setText("Informacja 1");
            panel.add(label1);
            panel.add(Save);
            panel.add(Load);
            panel.add(Tura);
            newframe = newFrame;
            newFrame.addKeyListener(new myKeyListener());
            newFrame.setFocusable(true);
            newFrame.requestFocusInWindow();
            newFrame.add(gridPanel);
            newFrame.add(panel);
            newFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                }
            });
            frame.dispose(); // Zamknięcie poprzedniego okna
            x = rows;
            y = columns;
            Organizmy.add(new Czlowiek(x/2,y/2,swiat));
            if (x >= 6 && y >= 6) {
                Organizmy.add(new Owca(1, 1, swiat));
                Organizmy.add(new Owca(1, 2, swiat));
                Organizmy.add(new Wilk(3, 3, swiat));

            }
            if (x >= 11 && y >= 11) {
                Organizmy.add(new Antylopa(8, 8, swiat));
                Organizmy.add(new Mlecz(10, 10, swiat));
                Organizmy.add(new Trawa(6, 6, swiat));
                Organizmy.add(new Zolw(8, 9, swiat));
            }
            if (x >= 21 && y >= 21) {
                Organizmy.add(new BarszczSosnowskiego(20, 20, swiat));
                Organizmy.add(new Guarana(12, 12, swiat));
                Organizmy.add(new Lis(17, 17, swiat));
                Organizmy.add(new WilczeJagody(15, 15, swiat));
            }

            newFrame.setVisible(true);
            Rozgrywka();
        }
    }
    private class PanelClickListener implements MouseListener {
        private final int row;
        private final int column;
        public PanelClickListener(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JFrame mainFrame;
            mainFrame = new JFrame("Dodaj Zwierze");
            mainFrame.setLayout(new GridLayout(2, 1));
            String[] nazwyzwierzat = {"Barszcz", "Guarana", "Mlecz", "Trawa", "Wilcze_Jagody","Antylopa", "Lis", "Wilk", "Zółw"};
            JList<String> listaZwierzat = new JList<>(nazwyzwierzat);
            listaZwierzat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listaZwierzat.setSelectedIndex(0);
            listaZwierzat.setVisibleRowCount(3);
            JScrollPane scrollPane = new JScrollPane(listaZwierzat);
            JButton Dodaj = new JButton("Dodaj");
            Dodaj.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (listaZwierzat.getSelectedIndex() != -1) {
                        Organizm temp = null;
                        String data = (String) listaZwierzat.getSelectedValue();
                        switch (data) {
                            case "Barszcz" -> {
                                temp = new BarszczSosnowskiego(row, column, swiat);
                            }
                            case "Guarana" -> {
                                temp = new Guarana(row, column, swiat);
                            }
                            case "Mlecz" -> {
                                temp = new Mlecz(row, column, swiat);
                            }
                            case "Trawa" -> {
                                temp = new Trawa(row, column, swiat);
                            }
                            case "Wilcze_Jagody" -> {
                                temp = new WilczeJagody(row, column, swiat);
                            }
                            case "Antylopa" -> {
                                temp = new Antylopa(row, column, swiat);
                            }
                            case "Lis" -> {
                                temp = new Lis(row, column, swiat);
                            }
                            case "Owca" -> {
                                temp = new Owca(row, column, swiat);
                            }
                            case "Wilk" -> {
                                temp = new Wilk(row, column, swiat);
                            }
                            case "Zółw" -> {
                                temp = new Zolw(row, column, swiat);
                            }
                        }
                        assert temp != null;
                        setOrganizmy(temp);
                        temp.Rysowanie();
                        RysujSwiat();
                        mainFrame.dispose();
                    }

                }
            });
            mainFrame.add(scrollPane);
            mainFrame.add(Dodaj);

            mainFrame.pack();
            mainFrame.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
    private class myKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyCode = e.getKeyCode();
            if(keyCode == KeyEvent.VK_U){
                for(Organizm org : Organizmy){
                    if(org instanceof Czlowiek){
                        ((Czlowiek) org).RuchCzlowiek(keyCode);
                        keyCode = 0;
                        //newframe.requestFocusInWindow();
                        return;
                    }
                }
            }
            System.out.println(keyCode);
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private Border createBorder() {
        int thickness = 1; // Grubość obramowania
        Color color = Color.BLACK; // Kolor obramowania
        return BorderFactory.createLineBorder(color, thickness);
    }

    public void Rozgrywka(){
        Legenda();
        RysujSwiat();
    }
    public void WykonajTure(){
        Thread thread = new Thread(() -> {
        //while (true) {
            Collections.sort(Organizmy);
            Iterator<Organizm> iterator = Organizmy.iterator();
            while (iterator.hasNext()) {
                Organizm org = iterator.next();
                if(org.isDousuniecia()){
                    iterator.remove();
                }else{
                    org.Akcja();
                    if(org.isDousuniecia()){
                        iterator.remove();
                    }
                }
            }
            Organizmy.addAll(noweOrganizmy);
            noweOrganizmy.clear();
            //Collections.sort(Organizmy);
            newframe.requestFocusInWindow();
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        //}
        });
        thread.start();
    }
    public void RysujSwiat(){
        for(Organizm org: Organizmy) {
            panels[org.getX()][org.getY()].setBackground(org.Kolor());
        }
    }
    public void Legenda(){
        // wypisanie legendy
        informacja.setText("Gra w życie");
    }

    public Vector<Organizm> getOrganizmy() {
        return Organizmy;
    }

    public void setOrganizmy(Vector<Organizm> organizmy) {
        Organizmy = organizmy;
    }

    public void setOrganizmy(Organizm organizmy) {
        Organizmy.add(organizmy);
    }

    public void clearOrganizmy() {
        Organizmy.clear();
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

    public JPanel[][] getPanels() {
        return panels;
    }

    public JLabel getInformacja() {
        return informacja;
    }

    public void setInformacja(String informacja) {
        this.informacja.setText(informacja);
    }

    public void addNoweOrganizmy(Organizm noweOrganizmy) {
        this.noweOrganizmy.add(noweOrganizmy);
    }

    public int getKeyCode() {
        return keyCode;
    }
}
