package GUI;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Menu {
    private JMenuBar menuBar;
    private JMenu menu;
    private JCheckBoxMenuItem chkMenuItemGridIsOn;
    private JMenuItem menuItemNewDungeon;
    private JMenuItem menuItemFastDungeon;
    private JMenuItem menuItemOpenDungeon;
    private JMenuItem menuItemSaveDungeon;
    private JMenuItem menuItemExportDungeon;
    private JMenuItem menuItemQuit;

    public Menu(){
        menuBar = new JMenuBar();
        menu = new JMenu();
        menuItemNewDungeon = new JMenuItem();
        menuItemFastDungeon = new JMenuItem();
        menuItemOpenDungeon = new JMenuItem();
        menuItemSaveDungeon = new JMenuItem();
        menuItemExportDungeon = new JMenuItem();
        menuItemQuit = new JMenuItem();
        chkMenuItemGridIsOn = new JCheckBoxMenuItem();
        createFileMenu();
        createOptionMenu();
    }

    public void createOptionMenu(){
        menu = new JMenu();
        menu.setText("Option");
        createGridOption();
        menuBar.add(menu);

    }

    public void createFileMenu(){
        menu = new JMenu();
        menu.setText("File");
        createNewItem();
        createFastItem();
        createOpenItem();
        createSaveItem();
        createExportAsImageItem();
        createQuitItem();
        menuBar.add(menu);
    }

    // Create the new dungeon option
    public void createNewItem(){
        menuItemNewDungeon = new JMenuItem();
        menuItemNewDungeon.setText("New Dungeon");
        menu.add(menuItemNewDungeon);
    }

    // Create the fast new dungeon option
    public void createFastItem(){
        menuItemFastDungeon = new JMenuItem();
        menuItemFastDungeon.setText("New Fast Dungeon");
        menu.add(menuItemFastDungeon);
    }

    // Create the save option
    public void createSaveItem(){
        menuItemSaveDungeon = new JMenuItem();
        menuItemSaveDungeon.setText("Save");
        menu.add(menuItemSaveDungeon);
    }

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

    public void createGridOption(){
        chkMenuItemGridIsOn = new JCheckBoxMenuItem();
        chkMenuItemGridIsOn.setText("isGridOn");
        chkMenuItemGridIsOn.setSelected(true);
        menu.add(chkMenuItemGridIsOn);
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }

    public JMenuItem getMenuItemNewDungeon(){
        return menuItemNewDungeon;
    }

    public JMenuItem getMenuItemFastDungeon(){
        return menuItemFastDungeon;
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

    public JCheckBoxMenuItem getChkMenuItemGridIsOn(){
        return  chkMenuItemGridIsOn;
    }
}
