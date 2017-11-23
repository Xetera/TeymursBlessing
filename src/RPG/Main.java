package RPG;

class Main {
    // for some weird fucking reason you HAVE to enter your program in the same class that you have your Application in
    // so in order to make the code less spaghetti and avoid working with Threading this is the method that gets called
    // once Window loads up.
    public static void game(Window window, Game game){
        window.print(Story.INTRO);
        Timur player =  new Timur(window);






        //window.
        //window.setSelfHealth(100);

    }

}

