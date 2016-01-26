package ca.bcit.comp2526.a1c.a1b;

/**
 * UserInterface.
 * 
 * @author your name here
 * @version
 */
public interface UserInterface
{
    void display(Person ... people);

    String readName();

    Person readPerson();

    void run(AddressBook book);

    void displayMsg(String msg);

    void displayErrorMsg(String msg);

}
