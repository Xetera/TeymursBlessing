package RPG;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Game {
    String name;
    Window window;
    // in order to make the handler know what to look out for, we're going to be updating
    // a status variable here like 'Your turn' or 'Traveling' to know how to handle different
    // commands at different times
    String status;
    public Game(Window instance) {
        this.window = instance;
        this.name = "Teymur's Blessing";
    }

    public void handler(String command){
        status = "standby";
        if (status.equals("standby")){

            if (command.equals("monster")){
                Enemy enemy = new Enemy(1, "Worm");
                encounterEnemy(enemy);
            }
            else if (command.equals("hide")) {
                window.setEnemyVisible(false);
            }
            else if (command.equals("health")){
                window.setSelfHealth(-101);
            }

        }
    }

    static void loseGame(Timur player){
        // "You lot the game at ";
        for (String name : player.stats.keySet()){
            System.out.println(player.stats.get(name));
        }
    }

    public void encounterEnemy(Enemy enemy){
    /*
    * Chances are this method is only gonna be called from the constructor of Enemy classes.
    * We will also be calling something like a 'battle phase' here to automatically update the UI to
    * show the names and health of of the monster we're encountering
    */

        window.print(String.format("You have encountered a level %d %s!", enemy.level, enemy.name));
    }

    // Pretty sure this is a useless method but I won't delete it in case we need it
    static void sleep(Integer seconds){
        // java has a weird way of timing out so we're just making this into a function that we can call
        // more conveniently
        try{
            Thread.sleep(1000 * seconds);
        }
        catch (java.lang.InterruptedException interrupt) {
            Thread.currentThread().interrupt();
        }
    }

}
