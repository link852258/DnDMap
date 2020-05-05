package GUI;

import Dungeon.Dungeon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFrame {
    private static JFrame mainFrame;
    private JMenuBar menuBar;
    private static Menu menu;
    private static JLabel label;
    private static JScrollPane scrollPane;
    private final int WIDTH;
    private final int HEIGHT;
    private static Dungeon dungeon;

    public MainFrame(int width, int height){
        mainFrame = new JFrame("DNDMap");
        menuBar = new JMenuBar();
        menu = new Menu();
        WIDTH = width;
        HEIGHT = height;
        loadJFrame();
        loadJMenuBar();
    }

    public static void show(){
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void removeImage(){
        scrollPane.remove(label);
        mainFrame.remove(scrollPane);
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    public static void reload(){
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    public void loadJFrame(){
        mainFrame.setSize(WIDTH,HEIGHT);
        mainFrame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        mainFrame.setLayout(new BorderLayout());
    }

    public static void addImage(){
        BufferedImage bi = dungeon.draw();
        menu.setImage(bi);
        label = new JLabel(new ImageIcon(bi));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(label);
        scrollPane.setPreferredSize(new Dimension(2000,2000));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVisible(true);
        mainFrame.add(scrollPane,BorderLayout.CENTER);
    }

    public void loadJMenuBar(){
        menuBar = menu.getMenuBar();
        mainFrame.add(menuBar, BorderLayout.NORTH);
    }

    public static void setDungeon(Dungeon dungeon){
        MainFrame.dungeon = dungeon;
        menu.setDungeon(dungeon);
    }
}
