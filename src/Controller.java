import Dungeon.Dungeon;
import GUI.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.*;

public class Controller {
    private MainFrame mainFrame;
    private Dungeon dungeon;
    private JFileChooser fileChooser;

    public Controller(MainFrame mainFrame, Dungeon dungeon){
        this.mainFrame = mainFrame;
        this.dungeon = dungeon;
        this.fileChooser = new JFileChooser();
        initDungeon();
    }

    public void initDungeon(){
        this.dungeon.generateDungeon();
    }

    public void initController(){
        mainFrame.getMenu().getMenuItemNewDungeon().addActionListener(actionEvent -> newDungeon());
        mainFrame.getMenu().getMenuItemFastDungeon().addActionListener(actionEvent -> fastDungeon());
        mainFrame.getMenu().getMenuItemOpenDungeon().addActionListener(actionEvent -> openFileDialog());
        mainFrame.getMenu().getMenuItemSaveDungeon().addActionListener(actionEvent -> saveFiledialog());
        mainFrame.getMenu().getMenuItemExportDungeon().addActionListener(actionEvent -> exportDungeon());
        mainFrame.getMenu().getMenuItemQuit().addActionListener(actionEvent -> quit());
        mainFrame.getDsf().getBtnOK().addActionListener(actionEvent -> createNewDungeon());
        mainFrame.getDsf().getBtnCancel().addActionListener(actionEvent -> closeDSF());
        mainFrame.addImage(dungeon);
        mainFrame.show();
    }

    public void newDungeon(){
        mainFrame.getDsf().show();
        mainFrame.removeImage();
        mainFrame.addImage(dungeon);
        mainFrame.reload();
    }

    public void fastDungeon(){
        int minNbOfRooms = dungeon.getMIN_NB_OF_ROOMS();
        int maxNbOfRooms = dungeon.getMAX_NB_OF_ROOMS();
        int scaleNumber = dungeon.getScaleNumber();
        int sizeRatio = dungeon.getDUNGEON_SIZE_RATIO();
        dungeon = null;
        dungeon = new Dungeon(minNbOfRooms, maxNbOfRooms, scaleNumber, sizeRatio);
        dungeon.generateDungeon();
        mainFrame.removeImage();
        mainFrame.addImage(dungeon);
        mainFrame.reload();
    }

    public void openFileDialog(){
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("DND file","dnd"));
        int result = fileChooser.showOpenDialog(null);
        try {
            if(result == JFileChooser.APPROVE_OPTION) {
                File f = fileChooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(f.getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(fis);
                dungeon = (Dungeon) ois.readObject();
                mainFrame.removeImage();
                mainFrame.addImage(dungeon);
                mainFrame.reload();
                ois.close();
                fis.close();
                JOptionPane.showMessageDialog(null, "The map was generated without problem!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(null, ioe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(null, cnfe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveFiledialog(){
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("DND file","dnd"));
        int result = fileChooser.showSaveDialog(null);
        try {
            if (result == JFileChooser.APPROVE_OPTION) {
                File f = fileChooser.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(addExtension(f.getAbsolutePath(),".dnd"));
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(dungeon);
                oos.close();
                fos.close();
                JOptionPane.showMessageDialog(null, "The map was saved without problem!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (IOException ioe){
            JOptionPane.showMessageDialog(null, ioe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exportDungeon(){
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("BMP file","bmp"));
        int result = fileChooser.showSaveDialog(null);
        try {
            if (result == JFileChooser.APPROVE_OPTION) {
                BufferedImage bi = dungeon.draw();
                File f = new File(addExtension(fileChooser.getSelectedFile().getAbsolutePath(),".bmp"));
                ImageIO.write(bi, "bmp", f);
                JOptionPane.showMessageDialog(null, "The map was exported without problem!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (IOException ioe){
            JOptionPane.showMessageDialog(null,"Error on export","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void quit(){
        System.exit(0);
    }

    // Add the extension if needed
    public String addExtension(String path, String extension){
        if(!path.endsWith(extension)){
            path += extension;
        }
        return path;
    }

    public void createNewDungeon(){
        int minRoom = convertToInt(mainFrame.getDsf().getTxtWidth());
        int maxRoom = convertToInt(mainFrame.getDsf().getTxtHeight());
        int scale = convertToInt(mainFrame.getDsf().getTxtScale());
        int sizeRatio = convertToInt(mainFrame.getDsf().getTxtSizeRatio());
        dungeon = null;
        dungeon = new Dungeon(minRoom, maxRoom, scale, sizeRatio);
        dungeon.generateDungeon();
        closeDSF();
    }

    public void closeDSF(){
        mainFrame.getDsf().close();
    }

    public int convertToInt(String information){
        int info = Integer.parseInt(information);
        return info;
    }

}
