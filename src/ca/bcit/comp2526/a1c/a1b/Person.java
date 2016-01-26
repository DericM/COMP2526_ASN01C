package ca.bcit.comp2526.a1c.a1b;

/**
 * Person stores a name and phone number.
 * 
 * @author your name here
 * @version
 */
public class Person {
    private final String name;
    private final String phone;

    /**
     * Constructor for objects of type Person.
     * @param name A String
     * @param phone A String
     */
    public Person(final String name, final String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Returns name as a String.
     * @return WHAT DOES THIS RETURN?
     */
    public String getName() {
        return (name);
    }

    /**
     * Returns phone number as a String.
     * @return WHAT DOES THIS RETURN?
     */
    public String getPhoneNumber() {
        return (phone);
    }
}
