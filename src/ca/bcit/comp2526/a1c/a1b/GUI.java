package ca.bcit.comp2526.a1c.a1b;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;

/**
 * GUI.
 * 
 * @author Deric
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GUI extends JFrame implements UserInterface {
    private AddressBook addressBook;// interface to database
    private String choice;// users choice 1-5
    
    private JPanel  menu;
    private JButton bAdd;

    private JButton bDelete;

    private JButton bDisplayAll;

    private JButton bSearch;

    private JButton bExit;

    
    

    /**
     * Constructor for objects of type GUI. Constructs the GUI and adds a
     * keyboard listener to capture the user's choices from the menu.
     */
    public GUI() {
        setSize(400, 400);// fix window size
        setVisible(true);// make window visible
        
        
        menu = new JPanel();
        
        
        
        bAdd = new JButton("Add");
        bDelete = new JButton("Delete");
        bDisplayAll = new JButton("DisplayAll");
        bSearch = new JButton("Search");
        bExit = new JButton("Exit");
        
        
        
        menu.add(bAdd);
        menu.add(bDelete);
        menu.add(bDisplayAll);
        menu.add(bSearch);
        menu.add(bExit);
        
        
        //addActionListener(new ButtonListener());
        
        add(menu);
        
    }

    /**
     * Displays the people records passed in.
     * 
     * @param people
     *            Person[] - records of people (from address book)
     */
    public void display(Person... people) {
        String msg = "";
        // create a string of all the enteries in the address book
        // no formating of the data - chose to keep it simple
        for (Person p : people) {
            msg += p.getName() + "    ";
            msg += p.getPhoneNumber() + "\n";
        }
        JOptionPane.showMessageDialog(this, msg, "Address book enteries", JOptionPane.PLAIN_MESSAGE);
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
    public Person readPerson() {
        final Person person;
        final String name;
        final String phone;
        name = readName();// since we have a method to read the name already
        phone = JOptionPane.showInputDialog("Enter the persons phone number");
        if (name == null || phone == null)// make sure we have data to create a
                                          // person
            return null;
        person = new Person(name, phone);

        return (person);
    }

    /**
     * Sets the AddressBook to use so the GUI can communicate with it. Note that
     * since a GUI is event driven unlike the Console this method has limited
     * use here.
     * 
     * @param book
     *            AddressBook - interface to the database of Person records
     */
    public void run(AddressBook book) {
        addressBook = book;
    }

    /**
     * Invokes the appropriate method on the addressBook. When the user makes
     * their selection the Keyboard listener stores the selection value in data
     * member "choice" and then calls this method.
     */
    private void evaluateChoice() {

        switch (choice) {
        case "Add":
            addressBook.addPerson();
            break;
        case "Delete":
            addressBook.deletePerson();
            break;
        case "DisplayAll":
            addressBook.findPerson();
            break;
        case "Search":
            addressBook.displayAll();
            break;
        case "Exit":
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
        
        this.add(menu);
        Color c = this.getBackground();// colour to clear screen with
        g.setColor(c);// use that colour
        // colour in a rectangle the size of the window with that colour
        
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        
        
        
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

    /**
     * Displays a message on the title bar of the window.
     * 
     * @param msg
     *            String - non-error message to display
     */
    public void displayMsg(String msg) {
        setTitle(msg);
    }

    /**
     * Displays an error message in a dialog box.
     * 
     * @param msg
     *            String - error msg to display
     */
    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /*
     * KeyBoardInput.
     *
     * A private (no one else needs access to this class) inner class (this
     * class needs access to the GUI to handle user selections) that listens for
     * keys pressed.
     *
     */
    private class ButtonListener implements ActionListener {



        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JButton selectedButton = (JButton) e.getSource();
                choice = selectedButton.getText(); 

            } catch (Exception except) {
                choice = "";// this will result in nothing happening
            }
            evaluateChoice(); // GUI method to call the addressBook to perform
                              // task
            
        }
    }
}
