/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import File.VehicleFile;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author monge55
 */
public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar jmenuBar;
    private JMenuItem jmiUpdate;
    private JMenuItem jmiRemove;
    private JMenuItem jmiInsert;
    private JMenuItem subMenu;
    private JMenuItem jmiToShow;
    private JMenuItem jmiCompress;
    private JMenu jmenu;
    public JDesktopPane desktopPane;
    VehicleFile vehicleFile;

    JButton jbtnAction;

    public MainWindow() {
        super();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//operacion cerrado
        this.setBackground(Color.blue);
        init();
    }

    private void init() {

        this.jmenuBar = new JMenuBar();
        this.add(this.jmenuBar);

        subMenu = new JMenu("Menu");
        jmenuBar.add(subMenu);
        this.jmenuBar.setBounds(10, 10, 60, 40);

        this.jmiUpdate = new JMenuItem("To update");
        this.jmiUpdate.addActionListener(this);
        subMenu.add(jmiUpdate);

        this.jmiRemove = new JMenuItem("Delete");
        this.jmiRemove.addActionListener(this);
        subMenu.add(jmiRemove);
        this.setJMenuBar(jmenuBar);

        this.jmiInsert = new JMenuItem("Register");
        this.jmiInsert.addActionListener(this);
        subMenu.add(jmiInsert);
        this.setJMenuBar(jmenuBar);
       
        this.jmiToShow = new JMenuItem("See car");
        this.jmiToShow.addActionListener(this);
        subMenu.add(jmiToShow);
        this.setJMenuBar(jmenuBar);
        
         this.jmiCompress = new JMenuItem("Compress File");
        this.jmiCompress.addActionListener(this);
        subMenu.add(jmiCompress);
        this.setJMenuBar(jmenuBar);
        
        
        
        desktopPane = new JDesktopPane();
        this.add(desktopPane);

    }//init

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jmiUpdate) {
            ToUpdate update = new ToUpdate();
            desktopPane.add(update);
            }
        else if(e.getSource() == this.jmiInsert){
            Insert insert = new Insert();
            desktopPane.add(insert);
        }else if(e.getSource() == this.jmiRemove){
            Delete delete= new Delete();
            desktopPane.add(delete);
        }else if(e.getSource() == this.jmiToShow){
            Show show = new Show();
            desktopPane.add(show);
        }else if(e.getSource() == this.jmiCompress){
            
        }
        
            
         //   vehicleFile.compress();
            
            
        
    }//action
}//class

