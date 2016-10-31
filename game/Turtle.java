/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Karol
 */
public class Turtle extends Animal {
    public Turtle(int x, int y, World world, boolean canMultiply){
    _world=world;
    _strength=2;
    _initiative=1;
    _age=0;
    _x=x;
    _y=y;
    _canMultiply=canMultiply;
    _iconFileName="turtle.png";
    world.setCreature(this);
    ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
    _image = ii.getImage();
    }
    
    public Turtle(int x, int y, World world, boolean canMultiply, int age, int strength){
    _world=world;
    _strength=strength;
    _initiative=1;
    _age=age;
    _x=x;
    _y=y;
    _canMultiply=canMultiply;
    _iconFileName="turtle.png";
    world.setCreature(this);
    ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
    _image = ii.getImage();
    }
    
    public void action(){
        _age++;
        Random generator = new Random();
        if (generator.nextInt(4)==1) {
            int actualX = _x;
    int actualY=_y;
    int newX = _x, newY=_y;
    //Random generator = new Random();
    int direction = generator.nextInt(4);
        if (direction==Global.UP && _y>0) { //gora
            newY+=-Global.fieldSize;
        }
        if (direction==Global.DOWN && _y<Global.fieldSize*Global.mapSize-Global.fieldSize) {
            newY+=Global.fieldSize;
        }
        if (direction==Global.LEFT && _x>0) {
            newX+=-Global.fieldSize;
        }
        if (direction==Global.RIGHT && _x<Global.fieldSize*Global.mapSize-Global.fieldSize) {
            newX+=Global.fieldSize;
        }
        
        if (newX!=actualX || newY!=actualY) {
            if (!this._world.checkIfSomethingIsThere(newX, newY)) {
                 this._world.move(newX, newY, this);
                    _x=newX;
                    _y=newY;              
            }
            else {
                 if (this._world.specifyAppearance(newX, newY)==this._iconFileName && (newX!=_x || newY!=_y)) {
                    this._world.move(newX, newY, this);
                    _x=newX;
                    _y=newY;
                    if (_canMultiply) {
                    this.multiply(newX, newY);
                    }
                }
                 else{
                     this._world.makeThemFight(actualX, actualY, this, newX, newY);
                 }
                
            }
        }
        }
    }
    
    public void collision(Organism attacker, int x, int y, int actualX, int actualY){
        
        if (this._world.specifyStrength(actualX,actualY )>5) {
            this._world.move(actualX, actualY, attacker);
          
        }
        else {
        if (this._world.specifyStrength(actualX, actualY)>=this._strength) {
            for (int i = 0; i < this._world.countCreatures(); i++) {
                if (this._world.specifyX(i)==_x && this._world.specifyY(i)==_y) {
                    this._world.kill(i);
                    this._world.move(x, y, attacker);
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < this._world.countCreatures(); i++) {
                if (this._world.specifyX(i)==actualX && this._world.specifyY(i)==actualY) {
                    this._world.kill(i);
                    this._world.move(x, y, this);
                    break;
                }
            }
        }
        }
    }
    
    protected void multiply(int x, int y){
        Random generator = new Random();
    int direction = generator.nextInt(4);
        if (direction==Global.UP && _y>0) { //gora
            _y+=-Global.fieldSize;
        }
         if (direction==Global.UP && _y==0) { //gora
            _y+=Global.fieldSize;
        }
        if (direction==Global.DOWN && _y<Global.mapSize*Global.fieldSize) {
            _y+=Global.fieldSize;
        }
         if (direction==Global.DOWN && _y==Global.mapSize*Global.fieldSize) {
            _y+=-Global.fieldSize;
        }
        if (direction==Global.LEFT && _x>0) {
            _x+=-Global.fieldSize;
        }
         if (direction==Global.LEFT && _x==0) {
            _x+=Global.fieldSize;
        }
        if (direction==Global.RIGHT && _x<Global.mapSize*Global.fieldSize) {
            _x+=Global.fieldSize;
        }
        if (direction==Global.RIGHT && _x==Global.mapSize*Global.fieldSize) {
            _x+=-Global.fieldSize;
        }
        this._world.move(_x, _y, this);
        this._canMultiply=false;
        this._world.createCreature(x, y, new Turtle(x,y,this._world,true));
    }
    
}
