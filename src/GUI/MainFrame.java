package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JPanel panel;
    private JScrollPane scrollPane;
    private final int WIDTH;
    private final int HEIGHT;

    public MainFrame(int width, int height){
        mainFrame = new JFrame("DNDMap");
        menuBar = new JMenuBar();
        panel = new JPanel();
        WIDTH = width;
        HEIGHT = height;
        loadJFrame();
        loadJMenuBar();
        loadJPanel();
    }

    public void show(){
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void loadJFrame(){
        mainFrame.setSize(WIDTH,HEIGHT);
        mainFrame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        mainFrame.setLayout(new BorderLayout());
    }

    public void loadJPanel(){
    }

    public void addImage(Image bi){
        JLabel label = new JLabel(new ImageIcon(bi));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(label);
        scrollPane.setPreferredSize(new Dimension(2000,2000));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setVisible(true);
        mainFrame.add(scrollPane,BorderLayout.CENTER);
    }

    public void loadJMenuBar(){
        JMenu menu = new JMenu("Un menu");
        JMenuItem menuItem = new JMenuItem("allo");
        menu.add(menuItem);
        menuBar.add(menu);
        menu = new JMenu("Deux menu");
        menuBar.add(menu);
        menu = new JMenu("Trois menu");
        menuBar.add(menu);
        mainFrame.add(menuBar, BorderLayout.NORTH);
    }
}
