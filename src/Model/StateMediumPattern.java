/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author rahma
 */
public class StateMediumPattern implements StatePatternn {

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public int getTimeout() {
        return 10;
    }
    @Override
    public int getStrategy(){
        return 2;
    }
}
