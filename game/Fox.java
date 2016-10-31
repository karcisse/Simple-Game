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
public class Fox extends Animal{
    public Fox(int x, int y, World world, boolean canMultiply){
        _world=world;
        _strength=3;
        _initiative=7;
        _age=0;
        _x=x;
        _y=y;
        _canMultiply=canMultiply;
        _iconFileName="fox.png";
        world.setCreature(this);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
        _image = ii.getImage();
    }
    
     public Fox(int x, int y, World world, boolean canMultiply, int age, int strength){
        _world=world;
        _strength=strength;
        _initiative=7;
        _age=age;
        _x=x;
        _y=y;
        _canMultiply=canMultiply;
        _iconFileName="fox.png";
        world.setCreature(this);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
        _image = ii.getImage();
    }
    
    public void action(){
        _age++;
        int actualX=_x;
        int actualY=_y;
        int newX=_x, newY=_y;
         Random generator = new Random();
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
        
        while(this._world.specifyStrength(newX, newY)>this._strength){
            newX=actualX;
            newY=actualY;
            _x=actualX;
            _y=actualY;
             direction = generator.nextInt(4);
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
        this._world.createCreature(x, y, new Fox(x,y,this._world,true));
    }
}
