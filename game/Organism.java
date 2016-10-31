/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 *
 * @author Karol
 */
public class Organism {
    
     protected int _strength, _initiative, _x, _y, _age;
    protected World _world;
    boolean _canMultiply;
    protected Image _image;
    protected String _iconFileName;
    
    void action(){}
    
    public void collision(Organism attacker, int x, int y, int actualX, int actualY){}
    
    void draw(){}
    
    int whereToX(){
        if (this!=null) {
             return _x;
            }
        return 0;
       }
    
    int whereToY(){
        if (this!=null) {
             return _y;
        }
        return 0;
       }
    
    int specifyStrenght(){return this._strength;}
    
    void changeStrength(int howMuch){this._strength+=howMuch;}
    
    void setX(int x){
        if (this!=null) {
            this._x=x;
        }
        else{
            System.out.println("Error setting X");
        }
    }
    
    void setY(int y){
     if (this!=null) {
            this._y=y;
        }
        else{
            System.out.println("Error setting Y");
        }
    }
    
    int specifyInitiative(){return this._initiative;}
    
    int specifyAge(){return this._age;}
    
    String specifyAppearance(){
        return this._iconFileName;
    }
    
    public Image getImage() {return _image;}
    
    private void multiply(int x, int y){}
   
}
