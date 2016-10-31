/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Karol
 */
public class World extends Main {
    
    private Human human;
    ArrayList<Organism> creatures;
    JButton save;
    ImageIcon ii = new ImageIcon(this.getClass().getResource("ground.png"));
    private Image image=ii.getImage();
    
    public World(){
                creatures = new ArrayList<Organism>();
                human= new Human(this);	
    }
    public void moveHuman(int direction){
        human.move(direction);
    }
    
    public void activateHumanPower(){
        if (human.roundCounter>5) {
            human.activatePower();
        }
    }
    
    public void setCreature(Organism creature){
    creatures.add(creature);
        if (creatures.size()>1) {
            int tmpinitiative=7;
            ArrayList<Organism> tmp=new ArrayList<Organism>();;
            for (int i = 7; i >=0; i--) {
                for (int j = 0; j < creatures.size(); j++) {
                    if (creatures.get(j).specifyInitiative()==tmpinitiative) {
                        tmp.add(creatures.get(j));
                    }
                }
                tmpinitiative--;
            }
            creatures=tmp;
        }
    }
    
    public void followRound(){
        for (int i = 0; i < creatures.size(); i++) {
            creatures.get(i).action();
           // System.out.println(creatures.get(i).specifyAppearance()+" wiek "+creatures.get(i).specifyAge());
        }
    }
    
    public boolean checkIfSomethingIsThere(int x, int y){
        for (int i = 0; i < creatures.size(); i++) {
            if (creatures.get(i).whereToX()==x && creatures.get(i).whereToY()==y) {
                return true;
            }
        }
        return false;
    }
    
    public void move(int newX, int newY, Organism who){
        for (int i = 0; i < creatures.size(); i++) {
            if (creatures.get(i)==who) {
                creatures.get(i).setX(newX);
                creatures.get(i).setY(newY);
            }
        }
    };
    
    public int specifyStrength(int x, int y){
        for (int i = 0; i < creatures.size(); i++) {
            if (creatures.get(i).whereToX()==x && creatures.get(i).whereToY()==y) {
                return creatures.get(i).specifyStrenght();
            }
        }
        return 0;
    }
    
    public void createCreature(int x, int y, Organism newCreature){System.out.println("Narodzil sie nowy organizm w "+x/Global.fieldSize+" "+y/Global.fieldSize);}
    
    public void createOrganisms(int howMuch){
    createCreature(2*Global.fieldSize,2*Global.fieldSize, new Wolf(2*Global.fieldSize,2*Global.fieldSize,this,true));
        createCreature(4*Global.fieldSize,4*Global.fieldSize, new Wolf(4*Global.fieldSize,4*Global.fieldSize,this,true));

    createCreature(3*Global.fieldSize,3*Global.fieldSize, new Wolf(3*Global.fieldSize,3*Global.fieldSize,this,true));
    createCreature(5*Global.fieldSize,5*Global.fieldSize, new Sheep(5*Global.fieldSize,5*Global.fieldSize,this,true));
    createCreature(13*Global.fieldSize,13*Global.fieldSize, new Fox(13*Global.fieldSize,13*Global.fieldSize,this,true));
    createCreature(16*Global.fieldSize,15*Global.fieldSize, new Antelope(16*Global.fieldSize,15*Global.fieldSize,this,true));
    createCreature(10*Global.fieldSize,10*Global.fieldSize, new Turtle(10*Global.fieldSize,10*Global.fieldSize,this,true));
    createCreature(9*Global.fieldSize,9*Global.fieldSize, new Grass(9*Global.fieldSize,9*Global.fieldSize,this,true));
    createCreature(4*Global.fieldSize,4*Global.fieldSize, new Guarana(4*Global.fieldSize,4*Global.fieldSize,this,true));
    createCreature(7*Global.fieldSize,7*Global.fieldSize, new WolfBerries(7*Global.fieldSize,7*Global.fieldSize,this,true));
    createCreature(8*Global.fieldSize,8*Global.fieldSize, new Thistle(8*Global.fieldSize,8*Global.fieldSize,this,true));
    }
    
    public void makeThemFight(int x, int y, Organism attacker, int newX, int newY){
        for (int i = 0; i < creatures.size(); i++) {
            if (creatures.get(i).whereToX()==newX && creatures.get(i).whereToY()==newY) {
                creatures.get(i).collision(attacker, newX, newY, x, y);
            }
        }
    }
    
    public void kill(int i){
        System.out.println(creatures.get(i).specifyAppearance()+" umiera w "+creatures.get(i).whereToX()/Global.fieldSize+" "+creatures.get(i).whereToY()/Global.fieldSize);
        creatures.remove(i); 
    }
    
    public void changeStrength(int x, int y, int howMuch){
        for (int i = 0; i < creatures.size(); i++) {
            if (creatures.get(i).whereToX()==x && creatures.get(i).whereToY()==y) {
                creatures.get(i).changeStrength(howMuch);
            }
        }
    }
    
    public int specifyX(int i){return creatures.get(i).whereToX();}
    
    public int specifyY(int i){return creatures.get(i).whereToY();}
    
    public int countCreatures(){return creatures.size();}
    
    public String specifyAppearance(int x, int y){
        for (int i = 0; i < creatures.size(); i++) {
            if (creatures.get(i).whereToX()==x && creatures.get(i).whereToY()==y) {
                return creatures.get(i).specifyAppearance();
            }
        }
        return "x";
    }
    
    @Override
    public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
             for (int i = 0; i < Global.mapSize; i++) {
                for (int j = 0; j < Global.mapSize; j++) {
                     g2d.drawImage(this.image, i*Global.fieldSize, j*Global.fieldSize, this);
                }
          }
            for (int i = 0; i < creatures.size(); i++) {
                g2d.drawImage(creatures.get(i).getImage(), creatures.get(i).whereToX(), creatures.get(i).whereToY(), this);
            }
           
        }
    
    public void save() throws FileNotFoundException    {
        PrintWriter file = new PrintWriter("save.txt");
        file.println(creatures.size());
        for (int i = 0; i < creatures.size(); i++) {
            file.println(creatures.get(i).specifyAppearance()+" "+creatures.get(i).whereToX()+" "+creatures.get(i).whereToY()+" "+creatures.get(i).specifyAge()+" "+creatures.get(i).specifyStrenght());
        }
        file.close();
        JOptionPane.showMessageDialog(null, "Zapisano", "Sukces", 1);
    }
    
    public void load() throws FileNotFoundException{
         creatures.clear();
        
        File file = new File("save.txt");
        Scanner in = new Scanner(file);
        int creaturesNumber = in.nextInt();
        for (int i = 0; i < creaturesNumber; i++) {
            String species = in.next();
            int x = in.nextInt();
            int y = in.nextInt();
            int age=in.nextInt();
            int strength = in.nextInt();
            //System.out.println(species+" "+x+" "+y+" "+age+" "+strength);
            if (species.equals("antelope.png")) {
                createCreature(x,y, new Antelope(x,y,this,true,age,strength));
            }
             if (species.equals("fox.png")) {
                createCreature(x,y, new Fox(x,y,this,true,age,strength));
            }
              if (species.equals("grass.png")) {
                createCreature(x,y, new Grass(x,y,this,true,age,strength));
            }
               if (species.equals("guarana.png")) {
                createCreature(x,y, new Guarana(x,y,this,true,age,strength));
            }
                if (species.equals("human.png")) {
                createCreature(x,y, new Human(x,y,this,age,strength));
            }
                 if (species.equals("sheep.png")) {
                createCreature(x,y, new Sheep(x,y,this,true,age,strength));
            }
                  if (species.equals("thistle.png")) {
                createCreature(x,y, new Thistle(x,y,this,true,age,strength));
            }
                   if (species.equals("turtle.png")) {
                createCreature(x,y, new Turtle(x,y,this,true,age,strength));
            }
                    if (species.equals("wolf.png")) {
                createCreature(x,y, new Wolf(x,y,this,true,age,strength));
            }
                     if (species.equals("wolfberries.png")) {
                createCreature(x,y, new WolfBerries(x,y,this,true,age,strength));
            }
        }
       JOptionPane.showMessageDialog(null, "Wczytano", "Sukces", 1);
    }
}
