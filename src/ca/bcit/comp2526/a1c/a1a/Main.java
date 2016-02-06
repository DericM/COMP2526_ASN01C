package ca.bcit.comp2526.a1c.a1a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 * Main.
 * 
 * @author Deric
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Main extends JFrame{
    private String[] database;
    Scanner input;
    private int choice;// users choice 1-5


    /**
     * Constructor for Main, adds a database and scanner.
     */
    public Main() {
        database = new String[0];
        input = new Scanner(System.in);
    }

    /**
     * Adds a new entry to the database.
     * @param name The new database entry.
     */
    public void add(final String name) {
        String[] temp = new String[database.length + 1];
        System.arraycopy(database, 0, temp, 0, database.length);
        temp[database.length] = name;
        database = temp;
    }

    /**
     * Searches the database for a specific name and returns the index or -1.
     * @param name The name to be searched for.
     * @return The index of the name in the database, or -1 if not found.
     */
    @SuppressWarnings("resource")
    public int search(final String name) {
        String name2;

        for (int pos = 0; pos < database.length; pos++) {
            Scanner extract = new Scanner(database[pos]);
            name2 = extract.next();

            if (name.compareToIgnoreCase(name2) == 0) {
                return pos;
            }
        }
        return -1;
    }

    /**
     * Display the entry at the given index in the database.
     * @param pos the index location to be displayed.
     */
    @SuppressWarnings("resource")
    public String display(int pos) {
        String name, phone;
        Scanner extract = new Scanner(database[pos]);
        name = extract.next();
        phone = extract.next();
        String msg = String.format("%-20s%-15s\n", name, phone);
        return msg;
    }

    /**
     * Displays a heading to the screen.
     */
    public String displayHeading() {
        String heading1 = "Name";
        String heading2 = "Phone";
        String msg = String.format("%-20s%-15s\n", heading1, heading2);
        return msg;
    }
  
    /**
     * display the entire database.
     */
    public void displayAll() {
        String msg = displayHeading() + "\n";
        for (int i = 0; i < database.length; i++) {
            msg += display(i) + "\n";
        }
        JOptionPane.showMessageDialog(this, msg, "Address book enteries", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Removes a person with a given name from the database.
     * @param name of the person to be removed.
     * @return true if successful, false if not.
     */
    public boolean remove(final String name) {
        int pos = search(name);
        if (pos >= 0) {
            String[] temp = new String[database.length - 1];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
            database = temp;
            return true;
        }
        return false;
    }


    /**
     * Reads and then adds an entry to the database.
     */
    public void addPerson() {
        add(readPerson());
    }


    /**
     * Reads a name and then removes an entry from the database.
     */
    public void deletePerson() {
        String name = readName();
        if (!remove(name))
            JOptionPane.showMessageDialog (this, "Could not delete " + name, "Delete", JOptionPane.PLAIN_MESSAGE);
        else
            JOptionPane.showMessageDialog (this, name + " was deleted successfully", "Delete", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Reads a name and then finds a person in the database.
     */
    public void findPerson() {
        String name = readName();
        int pos = search(name);
        String msg;
        if (pos >= 0) {
            msg = displayHeading() + "\n";
            msg += display(pos);
        } else {
            msg = "No such person";
        }
        JOptionPane.showMessageDialog (this, msg, "Search", JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Sets defaults adds event listeners.
     */
    public void run(){
        setSize(400, 400);// fix window size
        setVisible(true);// make window visible
        addKeyListener(new KeyBoardInput());// listen to keyboard input
    }

    /**
     * Program entry point.
     * @param args
     */
    public static void main(String[] args) {
        new Main().run();

    }
    


    /**
     * Reads a Person's name using a dialog box.
     * 
     * @return String - name read in
     */
    public String readName() {

        final String name = JOptionPane.showInputDialog("Enter the persons name");

        return (name);

    }

    /**
     * Reads in a Person's data (name/phone) using two dialog boxes and creates
     * a Person object with the data.
     * 
     * @return Person - person data record
     */
    public String readPerson() {
        final String person;
        final String name;
        final String phone;
        name = readName();// since we have a method to read the name already
        phone = JOptionPane.showInputDialog("Enter the persons phone number");
        if (name == null || phone == null)// make sure we have data to create a
                                          // person
            return null;
        person = name + " " + phone;

        return (person);
    }

    
    /**
     * Invokes the appropriate method on the addressBook. When the user makes
     * their selection the Keyboard listener stores the selection value in data
     * member "choice" and then calls this method.
     */
    private void evaluateChoice() {

        switch (choice) {
        case 1:
            addPerson();
            break;
        case 2:
            deletePerson();
            break;
        case 3:
            findPerson();
            break;
        case 4:
            displayAll();
            break;
        case 5:
            System.exit(0);
            break;

        default:
            // should not get here
        }

    }

    /**
     * Clears and draws the main menu on the window.
     * 
     * @param g
     *            Graphics - device context to allow drawing on this window
     */
    private void displayMenu(Graphics g) {
        Color c = this.getBackground();// colour to clear screen with
        g.setColor(c);// use that colour
        // colour in a rectangle the size of the window with that colour
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);// set colour to draw with now to black
        g.drawString("1) Add", 100, 100);
        g.drawString("2) Delete", 100, 120);
        g.drawString("3) Search", 100, 140);
        g.drawString("4) Display All", 100, 160);
        g.drawString("5) Exit", 100, 180);
        
    }

    /**
     * Displays the menu when window requires repainting.
     * 
     * @param g
     *            Graphics - device context for the window to draw on
     */
    public void paint(Graphics g) {
        displayMenu(g);
    }



    /*
     * KeyBoardInput.
     *
     * A private (no one else needs access to this class) inner class (this
     * class needs access to the GUI to handle user selections) that listens for
     * keys pressed.
     *
     */
    private class KeyBoardInput extends KeyAdapter {

        /**
         * Responds when a key is pressed on the keyboard.
         * 
         * @param e
         *            KeyEvent - key pressed and other information
         */
        public void keyTyped(KeyEvent e) {
            // set the "choice" data member of the outer class GUI
            // to get the integer value, get the character value of the key
            // pressed, make it a string and ask the Integer class to parse it
            try {
                choice = Integer.parseInt("" + e.getKeyChar());
                // if it wasn't an integer key pressed then make an invalid
                // choice
            } catch (Exception except) {
                choice = -1;// this will result in nothing happening
            }
            evaluateChoice(); // GUI method to call the addressBook to perform
                              // task
        }
    }

    
}
