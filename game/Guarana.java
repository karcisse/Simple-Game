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
public class Guarana extends Plant {
    public Guarana(int x, int y, World world, boolean canMultiply){
    _world=world;
    _strength=0;
    _initiative=0;
    _age=0;
    _x=x;
    _y=y;
    _canMultiply=canMultiply;
    _iconFileName="guarana.png";
    world.setCreature(this);
    ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
    _image = ii.getImage();
    }
    
    public Guarana(int x, int y, World world, boolean canMultiply, int age, int strength){
    _world=world;
    _strength=strength;
    _initiative=0;
    _age=age;
    _x=x;
    _y=y;
    _canMultiply=canMultiply;
    _iconFileName="guarana.png";
    world.setCreature(this);
    ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
    _image = ii.getImage();
    }
    
    public void collision(Organism attacker, int x, int y, int actualX, int actualY){
        if (this._world.specifyStrength(actualX, actualY)>=this._strength) {
            for (int i = 0; i < this._world.countCreatures(); i++) {
                if (this._world.specifyX(i)==_x && this._world.specifyY(i)==_y) {
                    this._world.changeStrength(actualX, actualY, Global.guaranaStrenghtPLUS);
                    this._world.kill(i);
                    this._world.move(x, y, attacker);
                }
            }
        }
        else{
            for (int i = 0; i < this._world.countCreatures(); i++) {
                if (this._world.specifyX(i)==actualX && this._world.specifyY(i)==actualY) {
                    this._world.kill(i);
                    this._world.move(x, y, this);
                }
            }
            
        }
    }
    
    public void action(){
        _age++;
        Random generator = new Random();
        if (generator.nextInt(8)==6) {
            int actualX = _x;
            int actualY=_y;
            int newX = _x, newY = _y;            
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
            
            if (!this._world.checkIfSomethingIsThere(newX, newY)) {
                    if (_canMultiply) {
                    this._canMultiply=false;
                    this._world.createCreature(newX, newY, new Guarana(newX, newY, this._world, false));
                }
            }
            
            
        }
    }
}
