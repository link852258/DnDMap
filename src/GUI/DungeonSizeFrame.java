package GUI;

import javax.swing.*;
import java.awt.*;

public class DungeonSizeFrame {
    private JDialog frame;
    private JPanel panel;
    private JLabel lblWidth, lblHeight, lblScale, lblSizeRatio, lblGridOnOff, lblWorldMap;
    private JTextField txtWidth, txtHeight, txtScale, txtSizeRatio;
    private JCheckBox chkGridOnOff, chkWorldMap;
    private JButton btnOK, btnCancel;

    public DungeonSizeFrame(){
        frame = new JDialog();
        panel = new JPanel();
        lblWidth = new JLabel("Min number of rooms");
        lblHeight = new JLabel("Max number of rooms");
        lblScale = new JLabel("Scale");
        lblSizeRatio = new JLabel("Table size ratio");
        lblGridOnOff = new JLabel("Grid is on");
        lblWorldMap = new JLabel("Is world map");
        txtWidth = new JTextField();
        txtHeight = new JTextField();
        txtScale = new JTextField();
        txtSizeRatio = new JTextField();
        chkGridOnOff = new JCheckBox();
        chkWorldMap = new JCheckBox();
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        init();
    }

    public void init(){
        panel.setLayout(new GridLayout(7,2));
        panel.setPreferredSize(new Dimension(400,200));
        panel.add(lblWidth);
        panel.add(txtWidth);
        panel.add(lblHeight);
        panel.add(txtHeight);
        panel.add(lblScale);
        panel.add(txtScale);
        panel.add(lblSizeRatio);
        panel.add(txtSizeRatio);
        panel.add(lblGridOnOff);
        chkGridOnOff.setSelected(true);
        panel.add(chkGridOnOff);
        panel.add(lblWorldMap);
        chkWorldMap.setSelected(false);
        panel.add(chkWorldMap);
        panel.add(btnOK);
        panel.add(btnCancel);
        frame.setTitle("Spec of room");
        frame.setLayout(new BorderLayout());
        frame.setSize(400,200);
        frame.add(panel,BorderLayout.CENTER);
        frame.setModal(true);
        frame.setLocationRelativeTo(null);
    }

    public void show(){
        frame.validate();
        frame.repaint();
        frame.pack();
        frame.setVisible(true);
    }

    public void close(){
        frame.dispose();
    }

    public String getTxtWidth(){
        return txtWidth.getText();
    }

    public String getTxtHeight(){
        return txtHeight.getText();
    }

    public String getTxtScale(){
        return txtScale.getText();
    }

    public String getTxtSizeRatio() { return txtSizeRatio.getText(); }

    public JButton getBtnOK() {
        return btnOK;
    }

    public JButton getBtnCancel(){
        return btnCancel;
    }

    public JCheckBox getChkGridOnOff(){
        return chkGridOnOff;
    }
    public JCheckBox getChkWorldMap(){
        return chkWorldMap;
    }
}
