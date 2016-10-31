package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JPanel{
    public static void main(String[] args) throws InterruptedException {
               JFrame frame = new JFrame();
               
               World world = new World();
               
               JPanel bottom = new JPanel();
               bottom.setAlignmentX(Component.TOP_ALIGNMENT);
               bottom.setLayout(new BoxLayout(bottom,BoxLayout.X_AXIS));
               
               JPanel log = new JPanel();
               log.setAlignmentX(Component.TOP_ALIGNMENT);
               log.setLayout(new BoxLayout(log,BoxLayout.X_AXIS));
               
               JPanel keys = new JPanel();
               keys.setAlignmentX(Component.CENTER_ALIGNMENT);
               keys.setLayout(new BoxLayout(keys,BoxLayout.Y_AXIS));
               
               JPanel keyUP = new JPanel();
               keyUP.setAlignmentX(Component.CENTER_ALIGNMENT);
               keyUP.setLayout(new BoxLayout(keyUP,BoxLayout.X_AXIS));
               
               JPanel keysLR = new JPanel();
               keysLR.setAlignmentX(Component.CENTER_ALIGNMENT);
               keysLR.setLayout(new BoxLayout(keysLR,BoxLayout.X_AXIS));
               
               JPanel keyDOWN = new JPanel();
               keyDOWN.setAlignmentX(Component.CENTER_ALIGNMENT);
               keyDOWN.setLayout(new BoxLayout(keyDOWN,BoxLayout.X_AXIS));
               
               JPanel otherKeys = new JPanel();
               otherKeys.setAlignmentX(Component.CENTER_ALIGNMENT);
               otherKeys.setLayout(new BoxLayout(otherKeys,BoxLayout.Y_AXIS));
               
               JPanel menu = new JPanel();
               menu.setAlignmentX(Component.CENTER_ALIGNMENT);
               menu.setLayout(new BoxLayout(menu,BoxLayout.X_AXIS));
               
               JButton close = new JButton("koniec");
               close.addActionListener(new ActionListener(){
                    @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
               });
               
               JButton right = new JButton("Prawo");
               right.addActionListener(new ActionListener(){
                    @Override
            public void actionPerformed(ActionEvent event) {
                world.moveHuman(Global.RIGHT);
                world.followRound();
            }
               });
               
               JButton left = new JButton("lewo");
               left.addActionListener(new ActionListener(){
                    @Override
            public void actionPerformed(ActionEvent event) {
                world.moveHuman(Global.LEFT);
                world.followRound();
            }
               });
               
               JButton up = new JButton("Gora");
               up.addActionListener(new ActionListener(){
                    @Override
            public void actionPerformed(ActionEvent event) {
                world.moveHuman(Global.UP);
                world.followRound();
            }
               });
               
               JButton down = new JButton("Dol");
               down.addActionListener(new ActionListener(){
                    @Override
            public void actionPerformed(ActionEvent event) {
                world.moveHuman(Global.DOWN);
                world.followRound();
            }
               });
               
                JButton power = new JButton("Super moc");
               power.addActionListener(new ActionListener(){
                    @Override
            public void actionPerformed(ActionEvent event) {
                world.activateHumanPower();
            }
               });
               
               JButton save = new JButton("zapisz");
               save.addActionListener(new ActionListener(){
                    @Override
            public void actionPerformed(ActionEvent event) {
                        try {
                            world.save();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
               });
               
               JButton load = new JButton("laduj");
               load.addActionListener(new ActionListener(){
                    @Override
            public void actionPerformed(ActionEvent event) {
                        try {
                            world.load();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                             JOptionPane.showMessageDialog(null, "Blad, ERROR", "Blad", 1);
                             System.exit(0);
                        }
            }
               });
               
               bottom.add(world);
               bottom.add(Box.createHorizontalBox());
               keyUP.add(up);
               keyUP.add(Box.createRigidArea(new Dimension(10, 10)));
               keysLR.add(left);
               keysLR.add(Box.createRigidArea(new Dimension(10, 10)));
               keysLR.add(right);
               keysLR.add(Box.createRigidArea(new Dimension(10, 10)));
               keyDOWN.add(down);
               keyDOWN.add(Box.createRigidArea(new Dimension(10, 10)));
               otherKeys.add(power);
               otherKeys.add(Box.createRigidArea(new Dimension(10, 10)));
               otherKeys.add(save);
               otherKeys.add(Box.createRigidArea(new Dimension(10, 10)));
               otherKeys.add(load);
               otherKeys.add(Box.createRigidArea(new Dimension(10, 10)));
               otherKeys.add(close);
               otherKeys.add(Box.createRigidArea(new Dimension(10, 10)));
               keys.add(keyUP); 
               keys.add(Box.createRigidArea(new Dimension(10, 10)));
               keys.add(keysLR);
               keys.add(Box.createRigidArea(new Dimension(10, 10)));
               keys.add(keyDOWN);
               keys.add(Box.createRigidArea(new Dimension(10, 10)));
               keys.add(otherKeys);
               keys.add(Box.createRigidArea(new Dimension(10, 10)));
               menu.add(keys);
               bottom.add(menu);
               frame.add(bottom);
              
        frame.setSize(Global.fieldSize*Global.mapSize+16 + Global.UIsize, Global.fieldSize*Global.mapSize+39);
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        world.createOrganisms(1);
        while (true) {
            world.repaint();
        }
    }
	
    
}





