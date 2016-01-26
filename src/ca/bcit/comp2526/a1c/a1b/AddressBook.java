package ca.bcit.comp2526.a1c.a1b;

/**
 * AddressBook.
 * 
 * @author your name here
 * @version
 */
public class AddressBook {
    private final Database database;
    private final UserInterface userInterface;

    /**
     * Constructor for objects of type AddressBook.
     * 
     * @param userInterface
     *            The user interface
     */
    public AddressBook(final UserInterface userInterface) {
        this.userInterface = userInterface;
        database = new Database();
    }

    public void addPerson() {
        final Person person;

        person = userInterface.readPerson();
        if (person != null)
            database.add(person);
    }

    public void deletePerson() {
        final String name;

        name = userInterface.readName();

        if (!remove(name))
            userInterface.displayErrorMsg("Could not delete " + name);
        else
            userInterface.displayMsg(name + " was deleted successfully");
    }

    public void findPerson() {
        final String name;
        final Person person;

        name = userInterface.readName();
        person = search(name);

        if (person != null) {
            display(person);
        } else {
            userInterface.displayErrorMsg("No such person");
        }
    }

    private boolean remove(final String name) {
        return (database.removeByName(name) != null);
    }

    private Person search(final String name) {
        return (database.findByName(name));
    }

    public void displayAll() {
        userInterface.display(database.getAll());
    }

    private void display(final Person person) {
        userInterface.display(person);
    }
}
