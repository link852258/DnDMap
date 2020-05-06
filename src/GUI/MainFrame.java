package GUI;

import Dungeon.Dungeon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFrame {
    private  JFrame mainFrame;
    private JMenuBar menuBar;
    private  Menu menu;
    private  JLabel label;
    private  JScrollPane scrollPane;
    private final int WIDTH;
    private final int HEIGHT;

    public MainFrame(int width, int height){
        mainFrame = new JFrame("DNDMap");
        menuBar = new JMenuBar();
        menu = new Menu();
        WIDTH = width;
        HEIGHT = height;
        loadJFrame();
        loadJMenuBar();
    }

    public void show(){
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void removeImage(){
        scrollPane.remove(label);
        mainFrame.remove(scrollPane);
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    public void reload(){
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    public void loadJFrame(){
        mainFrame.setSize(WIDTH,HEIGHT);
        mainFrame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        mainFrame.setLayout(new BorderLayout());
    }

    public void addImage(Dungeon dungeon){
        BufferedImage bi = dungeon.draw();
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

    public Menu getMenu(){
        return menu;
    }
}
