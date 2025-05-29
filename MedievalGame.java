import java.util.Scanner;
import java.util.Objects;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class MedievalGame {

  /* Instance Variables */
  Player player;

  /* Main Method */
  public static void main(String[] args) {
    
    Scanner console = new Scanner(System.in);
    MedievalGame game = new MedievalGame();

    game.player = game.start(console);

    game.addDelay(500);
    System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
    System.out.println(game.player);

    game.addDelay(1000);
    System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
    game.save();

    game.addDelay(2000);
    System.out.println("We just saved your game...");
    System.out.println("Now we are going to try to load your character to make sure the save worked...");

    game.addDelay(1000);
    System.out.println("Deleting character...");
    String charName = game.player.getName();
    game.player = null;

    game.addDelay(1500);
    game.player = game.load(charName, console);
    System.out.println("Loading character...");

    game.addDelay(2000);
    System.out.println("Now let's print out your character again to make sure everything loaded:");

    game.addDelay(500);
    System.out.println(game.player);
  } // End of main

  /* Instance Methods */
  private Player start(Scanner console) {
    // Add start functionality here
    Player player;
    return player;
    Art.homeScreen();
    System.out.println("Would you like to start a new game or load a saved game?");
    System.out.println("Enter 'Y' to load a game or 'n' to create a new game:");
    
     while(true){
        String answer = console.next().toLowerCase();
        if (answer.equals("y")){
        System.out.println("Enter previous character name");
        String name = console.next();

        player.load(name, console);

        break;
        } else if (answer.equals("n")){
          System.out.println("Please enter desired player name: ");
          String newName = console.next();

          player = new Player(newName);

          break;
        } else {
          System.out.println("Please enter 'y' to load or 'n' to start a new character");
        }
     
      

    }

  } // End of start

  private void save() {
    // Add save functionality here
  String filename = player.name + ".svr";

  try{ FileOutputStream userSaveFile = new        
      FileOutputStream(filename);
      ObjectOutputStream playerSaver = new ObjectOutputStream(userSaveFile);
      playerSaver.writeObject(player);
    } catch (IOException e) {
      System.out.println("There was an error saving your game, your file might not be available the next time you go to load a game.");
    }
  } // End of save

  private Player load(String playerName, Scanner console) {
    // Add load functionality here
    Player loadedPlayer;
    String filename = player.name + ".svr";
    try {
      FileInputStream savedFile = new FileInputStream(filename);
      ObjectInputStream playerSaved = new ObjectInputStream(savedFile);
      
      loadedPlayer = (Player) savedFile.readObject();

      return loadedPlayer;
    } catch (IOException | ClassNotFoundException e){
      game.addDelay(1500);
      System.out.println("File not found");

      game.addDelay(2000);
      loadedPlayer = new Player(playerName);

    }
    return loadedPlayer;
  } // End of load

  // Adds a delay to the console so it seems like the computer is "thinking"
  // or "responding" like a human, not instantly like a computer.
  private void addDelay(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}