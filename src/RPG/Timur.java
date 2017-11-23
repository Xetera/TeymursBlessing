package RPG;

import java.util.HashMap;
import java.util.Map;

public class Timur {
    // setting up the variables that we're gonna be using
    Integer level;

    Integer maxExperience;
    Integer experience;

    Integer maxHealth;
    Integer health;
    Integer maxMana;
    public Boolean alive;
    private Window window;

    Map<String, Integer> stats = new HashMap<>();
    Map<String, Map<String, Object>> moves = new HashMap<>();

    Timur(Window instance){ // Constructor
        // defining the values of the variables inside of RPG.Timur
        window = instance;
        this.stats.put("Strength", 8);
        this.stats.put("Dexterity", 3);
        this.stats.put("Intelligence", 1);
        this.stats.put("Luck", 1);
        this.stats.put("Stamina", 2);
        this.stats.put("Vitality", 4);

        this.maxExperience = 50;
        this.experience = 0;

        this.newMove(
                "Minor Autism",
                "Hit your opponent with a small yet sturdy dose of autism.",
                10
        );

        this.maxHealth = 10 + (2 * this.stats.get("Stamina"));
        this.health = maxHealth;

    }
    public void newMove(String name, String description, Integer damage){
        // When a new move is registered\
        // create new hashmap to enter the Hashmap of moves
        Map<String, Object> move = new HashMap<>();
        // adds values
        move.put("Name", name);
        move.put("Description", description);
        move.put("Damage", damage);
        this.moves.put(name, move);
    }

    public void takeDamage(Integer damage, Enemy enemy){
        if (this.health - damage < 0) {
            Game.loseGame(this);
        }
        this.health -= damage;
        // setSelfHealth will increase health if positive and decrease if negative
        // that's why we are multiplying it by -1
        window.setSelfHealth(-1 * damage);
    }


}