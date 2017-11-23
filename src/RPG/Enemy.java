package RPG;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// This is the class where we're gonna have all the preset classes for Enemy.
// The default one is probably gonna be some sort of basic default monster but we can create new classes
// that extend Enemy if we want to add new Enemies that act differently than the base class (ex. special abilities).

class Enemy {

    Integer level;
    String name;
    Integer maxHealth;
    Integer health;
    Boolean alive;

    Enemy(Integer level,  String name) { // constructor
        this.name = name;
        this.level = level;
        this.maxHealth = 10 + (this.level * level);
        this.health = this.maxHealth;
        this.alive = true;
    }

    public void dealDamage(Timur player) {
        if (this.alive) {
            // get a random damage with:
            // MIN: level amount MAX: 3 times level amount
            // + 1 is a part of how nextInt() works
            Integer damage = ThreadLocalRandom.current().nextInt(this.level, (this.level * 3) + 1);
            player.takeDamage(damage, this);

        }
    }

    public void takeDamage(Integer damage, Timur player) {
        this.health -= damage;
        // we're gonna change these print outs to commands that display on the game window later
        System.out.println(this.name + " took " + damage + " damage!");
        Game.sleep(2);

    }
}