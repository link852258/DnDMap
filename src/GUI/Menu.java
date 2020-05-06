package GUI;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Menu {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemNewDungeon;
    private JMenuItem menuItemOpenDungeon;
    private JMenuItem menuItemSaveDungeon;
    private JMenuItem menuItemExportDungeon;
    private JMenuItem menuItemQuit;

    public Menu(){
        menuBar = new JMenuBar();
        menu = new JMenu();
        menuItemNewDungeon = new JMenuItem();
        menuItemOpenDungeon = new JMenuItem();
        menuItemSaveDungeon = new JMenuItem();
        menuItemExportDungeon = new JMenuItem();
        menuItemQuit = new JMenuItem();
        createFileMenu();
    }

    public void createFileMenu(){
        menu.setText("File");
        createNewItem();
        createOpenItem();
        createSaveItem();
        createExportAsImageItem();
        createQuitItem();
        menuBar.add(menu);
    }

    // TODO - Add the capacity to choose the size of the next dungeon
    // Create the new dungeon option
    public void createNewItem(){
        menuItemNewDungeon = new JMenuItem();
        menuItemNewDungeon.setText("New Dungeon");
        menu.add(menuItemNewDungeon);
    }

    // Create the save option
    public void createSaveItem(){
        menuItemSaveDungeon = new JMenuItem();
        menuItemSaveDungeon.setText("Save");
        menu.add(menuItemSaveDungeon);
    }

    // TODO - Add the capacity to choose the name and the location for the exported files
    // Create the export as image option
    public void createExportAsImageItem(){
        menuItemExportDungeon = new JMenuItem();
        menuItemExportDungeon.setText("Export as Image");
        menu.add(menuItemExportDungeon);
    }

    // Create open item option
    public void createOpenItem(){
        menuItemOpenDungeon = new JMenuItem();
        menuItemOpenDungeon.setText("Open");
        menu.add(menuItemOpenDungeon);
    }

    // Create the quit option
    public void createQuitItem(){
        menuItemQuit = new JMenuItem();
        menuItemQuit.setText("Quit");
        menu.add(menuItemQuit);
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }

    public JMenuItem getMenuItemNewDungeon(){
        return menuItemNewDungeon;
    }
    public JMenuItem getMenuItemOpenDungeon(){
        return menuItemOpenDungeon;
    }

    public JMenuItem getMenuItemSaveDungeon(){
        return menuItemSaveDungeon;
    }

    public JMenuItem getMenuItemExportDungeon(){
        return menuItemExportDungeon;
    }

    public JMenuItem getMenuItemQuit() {
        return menuItemQuit;
    }
}
