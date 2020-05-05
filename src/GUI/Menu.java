package GUI;

import Dungeon.Dungeon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Menu {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private BufferedImage bi;
    private Dungeon dungeon;

    public Menu(){
        menuBar = new JMenuBar();
        menu = new JMenu();
        menuItem = new JMenuItem();
        createFileMenu();
    }

    public void createFileMenu(){
        menu.setText("File");
        createOpenItem();
        createSaveItem();
        createExportAsImageItem();
        createQuitItem();
        menuBar.add(menu);
    }

    // Create the save option
    public void createSaveItem(){
        menuItem = new JMenuItem();
        menuItem.setText("Save");
        menuItem.addActionListener(actionEvent -> {
            try {
                FileOutputStream fos = new FileOutputStream("Dungeon.dnd");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(dungeon);
                oos.close();
                fos.close();
                JOptionPane.showMessageDialog(null,"The map was saved without problem!","Success", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ioe){
                JOptionPane.showMessageDialog(null, ioe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        menu.add(menuItem);
    }

    // Create the export as image option
    public void createExportAsImageItem(){
        menuItem = new JMenuItem();
        menuItem.setText("Export as Image");
        menuItem.addActionListener(actionEvent -> {
            try {
                File f = new File("allo.jpg");
                ImageIO.write(bi, "jpg", f);
                f = new File("allo.gif");
                ImageIO.write(bi, "gif", f);
                f = new File("allo.bmp");
                ImageIO.write(bi, "bmp", f);
                JOptionPane.showMessageDialog(null,"The map was exported without problem!","Success", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ioe){
                JOptionPane.showMessageDialog(null,"Error on export","Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        menu.add(menuItem);
    }

    // Set the mapImage for the export
    public void setImage(BufferedImage bi){
        this.bi = bi;
    }

    public void setDungeon(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    // Create open item option
    public void createOpenItem(){
        menuItem = new JMenuItem();
        menuItem.setText("Open");
        menuItem.addActionListener(actionEvent -> {
            try {
                FileInputStream fis = new FileInputStream("Dungeon.dnd");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Dungeon d = (Dungeon)ois.readObject();
                setDungeon(d);
                MainFrame.setDungeon(d);
                MainFrame.removeImage();
                MainFrame.addImage();
                MainFrame.reload();
                ois.close();
                fis.close();
                JOptionPane.showMessageDialog(null,"The map was generated without problem!","Success", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null, ioe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (ClassNotFoundException cnfe){
                JOptionPane.showMessageDialog(null, cnfe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        menu.add(menuItem);
    }

    // Create the quit option
    public void createQuitItem(){
        menuItem = new JMenuItem();
        menuItem.setText("Quit");
        menuItem.addActionListener(actionEvent -> {
            System.exit(0);
        });
        menu.add(menuItem);
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }
}
