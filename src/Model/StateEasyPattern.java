package Model;



public class StateEasyPattern implements StatePatternn {

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getTimeout() {
        return 2;
    }
    @Override
    public int getStrategy(){
        return 1;
    }

}
