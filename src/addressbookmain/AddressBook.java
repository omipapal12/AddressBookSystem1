package addressbookmain;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AddressBook {
    public static final ArrayList<Contact> contactList = new ArrayList<>();
    public static Map<String, Contact> nameHashMap = new HashMap<String, Contact>();
    public static Map<String, Contact> cityHashMap = new HashMap<String, Contact>();
    public static Map<String, Contact> stateHashMap = new HashMap<String, Contact>();

    static Scanner sc = new Scanner(System.in);
    static AddressBook addressBook = new AddressBook();

    public boolean addContact(Contact contact) {
        List<Contact> checkByName = searchByName(contact.getFirstName());
        for (Contact equalName : checkByName)
            if (equalName.equals(contact))
                return false;
        contactList.add(contact);
        return true;
    }

    // method for search contact by name
    public List<Contact> searchByName(String name) {// collection list of element
        // stream and lambda for find filter given name from arraylist
        return contactList.stream().filter(person -> person.getFirstName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByCity(String city) {
        return contactList.stream().filter(person -> person.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByState(String state) {
        return contactList.stream().filter(person -> person.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    // Method to view person
    public static void viewByName(Map<String, Contact> nameHashMap) {
        nameHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public static void viewByCity(Map<String, Contact> cityHashMap) {
        cityHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public static void viewByState(Map<String, Contact> stateHashMap) {
        stateHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public List<Contact> sortBy(Function<? super Contact, ? extends String> key) {
        return contactList.stream().sorted(Comparator.comparing(key)).collect(Collectors.toList());
    }

    public List<Contact> sortByZip(Function<? super Contact, ? extends Long> key) {
        return contactList.stream().sorted(Comparator.comparing(key)).collect(Collectors.toList());
    }

    // method for edit contact
    public boolean editContact(Contact current, Contact edit) {
        if (!contactList.contains(current))
            return false;
        contactList.remove(current);
        contactList.add(edit);
        return true;
    }

    // method for delete contact
    public boolean deleteContact(Contact contacts) {
        contactList.remove(contacts);
        return true;
    }

    // for showing output details
    @Override
    public String toString() {
        if (contactList.isEmpty())
            return "No contacts found!";
        String result = new String();
        for (int i = 0; i < contactList.size(); i++) {
            result += " " + contactList.get(i);
        }
        return result;
    }

    // method for adding details
    public static Contact readContact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter City: ");
        String city = sc.nextLine();
        System.out.print("Enter State: ");
        String state = sc.nextLine();
        System.out.print("Enter Zip Code: ");
        String zip = sc.next();
        sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.next();
        sc.nextLine();
        System.out.print("Enter Email ID: ");
        String email = sc.nextLine();
        return new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
    }

    // method for show option for contacts
    public static void addressBookOptions(AddressBook addressBook) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n-------------------------- Address Book Contact Option --------------------------");
            System.out.println("1. Add contact details");
            System.out.println("2. Edit contact details");
            System.out.println("3. Delete contact details");
            System.out.println("4. Show contacts details");
            System.out.println("5. Sort Address Book");
            System.out.println("6. Back to main menu");
            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    if (addressBook.addContact(readContact())) // call addcontact with passing method readcontact
                        System.out.println("Cotact Added Successfully....!");
                    else
                        System.out.println("Contact Already Exist....!");
                    break;
                case 2:
                    System.out.println("Enter First name to edit contact: ");
                    String name = sc.nextLine();
                    List<Contact> equalName = addressBook.searchByName(name);// list of equal first name
                    if (equalName.isEmpty())// if not match found
                        System.out.println("Data Not Found....!");
                    else if (equalName.size() == 1) {// if only one equal match found
                        addressBook.editContact(equalName.get(0), readContact()); // call edit method with name and method
                        // parameter
                        System.out.println("Contact data modified....!");
                    } else {// if more than one firstname match found in equal name list
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.println("Enter index to edit : ");
                        int i = sc.nextInt();
                        sc.nextLine();
                        addressBook.editContact(equalName.get(i), readContact());
                        System.out.println("Contact Modified....!");
                    }
                    break;
                case 3:
                    System.out.println("Enter First name to delete contact: ");
                    name = sc.nextLine();
                    equalName = addressBook.searchByName(name);
                    if (equalName.isEmpty())
                        System.out.println("Data Not Found.....!");
                    else if (equalName.size() == 1) {
                        addressBook.deleteContact(equalName.get(0));
                        System.out.println("Contact data deleted....!");
                    } else {
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.println("Enter an index to delete");
                        int index = sc.nextInt();
                        sc.nextLine();
                        addressBook.deleteContact(equalName.get(index));
                        System.out.println("Cotact data deleted....!");
                    }
                    break;
                case 4:
                    System.out.println(addressBook.toString()); // call tostring method for showing details
                    break;
                case 5:
                    sortByOption();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid Choice!");
                    break;
            }
        }
    }

    public void searchByOptions() {
        AddressBook addressBook = new AddressBook();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. By name");
        System.out.println("2. By city");
        System.out.println("3. By state");
        System.out.println("4. Back");
        System.out.println("Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter name: ");
                String name = sc.nextLine();
                contactList.forEach(book -> searchByName(name).forEach(System.out::println));
                break;
            case 2:
                System.out.println("Enter city: ");
                String city = sc.nextLine();
                contactList.forEach(book -> searchByCity(city).forEach(System.out::println));
                break;
            case 3:
                System.out.println("Enter state: ");
                String state = sc.nextLine();
                contactList.forEach(book -> searchByState(state).forEach(System.out::println));
                break;
            case 4:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }

    }

    // method for view element by option
    public static void viewByOption(Map<String, AddressBook> addressBookMap) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. View By name");
        System.out.println("2. View By city");
        System.out.println("3. View By state");
        System.out.println("4. Back");
        System.out.print("Enter Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                viewByName(nameHashMap);
                break;
            case 2:
                viewByCity(cityHashMap);
                break;
            case 3:
                viewByState(stateHashMap);
                break;
            case 4:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }

    // method to count element by option
    public static void countByOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Count City ");
        System.out.println("2. Count State");
        System.out.println("3. Back ");
        System.out.println("Enter Your Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                Map<String, Long> countCity = contactList.stream()
                        .collect(Collectors.groupingBy(e -> e.getCity(), Collectors.counting()));
                System.out.println(countCity + "\n");
                break;
            case 2:
                Map<String, Long> countState = contactList.stream()
                        .collect(Collectors.groupingBy(e -> e.getState(), Collectors.counting()));
                System.out.println(countState + "\n");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    public static void sortByOption() {
        System.out.println("1. By first name");
        System.out.println("2. By last name");
        System.out.println("3. By city");
        System.out.println("4. By state");
        System.out.println("5. By zip");
        System.out.println("6. Back");
        System.out.print("Your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                addressBook.sortBy(Contact::getFirstName).forEach(System.out::println);
                break;
            case 2:
                addressBook.sortBy(Contact::getLastName).forEach(System.out::println);
                break;
            case 3:
                addressBook.sortBy(Contact::getCity).forEach(System.out::println);
                break;
            case 4:
                addressBook.sortBy(Contact::getState).forEach(System.out::println);
                break;
            case 5:
                addressBook.sortBy(Contact::getZip).forEach(System.out::println);
                break;
            case 6:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
}

