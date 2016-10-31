/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;

/**
 *
 * @author Karol
 */
public class Animal extends Organism {
    public void action(){
        
    _age++;
    int actualX = _x;
    int actualY=_y;
    int newX = _x, newY=_y;
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
    
    public void collision(Organism attacker, int x, int y, int actualX, int actualY){
        //System.out.println(attacker._strength+"  col  "+this._strength);
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
    
    protected void multiply(int x, int y){}
}
   
