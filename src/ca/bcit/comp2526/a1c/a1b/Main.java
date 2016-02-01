package ca.bcit.comp2526.a1c.a1b;

/**
 * Main.
 * 
 * @author your name here
 * @version
 */
public class Main {
    /**
     * The main method accepts command line arguments and drives the program.
     * 
     * @param args
     *            specify choice of console or gui
     */
    public static void main(String[] args) {
        UserInterface ui = null;
        AddressBook book;
        if (args.length > 0) {
            if (args[0].compareToIgnoreCase("console") == 0) {
                ui = new ConsoleUserInterface();
            } else if (args[0].compareToIgnoreCase("gui") == 0) {
                ui = new GUI();
            }
        }
        if (ui != null) {
            book = new AddressBook(ui);
            ui.run(book);
        }
    }
}
