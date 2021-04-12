package Daemon;

import Game.Game;

public class Daemon implements Runnable{

    //valorea default
    float timeToWaitSeconds = 10;
    private boolean isRunnable = true;


    public Daemon() {
    }

    public Daemon(float timeToWaitSeconds) {
        this.timeToWaitSeconds = timeToWaitSeconds;
    }

    @Override
    public void run() {
        long Start = System.nanoTime();
        long End = System.nanoTime();

        while((End - Start)/1_000_000_000 <= timeToWaitSeconds && isRunnable) {
            End = System.nanoTime();
        }

        System.out.println("GATA ASTEPTAREA");

        Game.getInstance().stopGame();
    }

    public void setRunnable(boolean runnable) {
        isRunnable = runnable;
    }
}
