package addressbookmain;
import java.util.*;
import java.util.stream.Collectors;


public class AddressBook {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Contact> personDetailsList = new ArrayList<Contact>();
    static HashMap<String, ArrayList<Contact>> hashmap = new HashMap<>();

    static AddressBook personDetails = new AddressBook();

    /*
     * --------- Add Person Details and store in array
     */
    public void addDetails() {

        // taking value from user
        Contact details = new Contact();
        System.out.println("Add person Details");
        System.out.print("Enter First Name :: ");
        details.setFirstName(sc.next());
        System.out.print("Enter Last Name :: ");
        details.setLastName(sc.next());
        System.out.print("Enter City :: ");
        details.setCity(sc.next());
        System.out.print("Enter State :: ");
        details.setState(sc.next());
        System.out.print("Enter pinCode :: ");
        details.setZip(sc.next());
        System.out.print("Enter mobNumber :: ");
        details.setPhoneNumber(sc.next());
        System.out.print("Enter Email ID :: ");
        details.setEmail(sc.next());

        personDetailsList.add(details);
        // System.out.println(personDetailsList);
    }

    /*
     * Edit person details through person 1st name
     */
    public void editDetails() {
        System.out.println("Confirm your first name to edit details: ");
        String confirm_name = sc.next();

        for (int i = 0; i < personDetailsList.size(); i++) {
            if (personDetailsList.get(i).getFirstName().equals(confirm_name)) {
                System.out.println("Select form below to change: ");
                System.out.println(
                        "\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Zip\n7.Mobile number\n8.Email");
                int edit = sc.nextInt();

                switch (edit) {
                    case 1:
                        System.out.println("Enter new first name");
                        personDetailsList.get(i).setFirstName(sc.next());
                        break;
                    case 2:
                        System.out.println("Enter new Last name");
                        personDetailsList.get(i).setLastName(sc.next());
                        break;
                    case 3:
                        System.out.println("Enter new Address");
                        personDetailsList.get(i).setAddress(sc.next());
                        break;
                    case 4:
                        System.out.println("Enter new City");
                        personDetailsList.get(i).setCity(sc.next());
                        break;
                    case 5:
                        System.out.println("Enter new State");
                        personDetailsList.get(i).setState(sc.next());
                        break;
                    case 6:
                        System.out.println("Enter new Zip");
                        personDetailsList.get(i).setZip(sc.next());
                        break;
                    case 7:
                        System.out.println("Enter new Mobile number");
                        personDetailsList.get(i).setPhoneNumber(sc.next());
                        break;
                    case 8:
                        System.out.println("Enter new E-mail");
                        personDetailsList.get(i).setEmail(sc.next());
                        break;
                }
                System.out.println("Edited list is: ");
                System.out.println(personDetailsList);

            } else
                System.out.println("Please Enter valid First name");
        }

    }

    /*
     * Display person details
     */
    public void Display_All(ArrayList<Contact> arr) {
        boolean is_Empty = arr.isEmpty();
        if (is_Empty == true)
            System.out.println("Array List is Empty");
        else
            System.out.println(arr);
    }

    /*
     * Method to check for duplicate entry before adding the person.
     */
    public void duplicateCheck(String firstName) {
        for (int k = 0; k < personDetailsList.size(); k++) {
            String contactName = personDetailsList.get(k).getFirstName();

            if (firstName.equals(contactName)) {
                System.out.println("This Person is Already Present");
            } else {
                System.out.println("You can Add this Person");
                break;
            }
        }
    }

    public void searchbycity(String city) {
        for (int k = 0; k < personDetailsList.size(); k++) {
            String contactName = personDetailsList.get(k).getCity();

            if (city.equals(contactName)) {
                System.out.println("This Person is Already Present");
            } else {
                System.out.println("You can Add this Person");
                break;
            }
        }
    }

    /*
     * Delete person details through person 1st name
     */
    public boolean deleteContact(String name) {
        int flag = 0;
        for (Contact contact : personDetailsList) {
            if (contact.getFirstName().equals(name)) {
                personDetailsList.remove(contact);
                flag = 1;
                break;
            }
        }
        if (flag == 1)
            return true;
        else
            return false;
    }

    /*
     * create multiple address book
     */

    public static void createAddressBook() {
        boolean flag = true;
        while (flag) {
            System.out.println("choose what you want to do :: ");
            System.out.println(
                    "1.Create new address book.\n2.Edit existing address book.\n3.Display all address books.\n4.Check Duplicate Name.\n5.Seach Person By City.\n6.Exit");
            int choose = sc.nextInt();

            if (choose == 6) {
                System.out.println("Exit");
                break;
            }

            switch (choose) {
                case 1:
                    System.out.println("Enter the name of Address Book :: ");
                    String addressBook = sc.next();
                    if (hashmap.containsKey(addressBook)) {
                        System.out.println("Address Book name already exit please enter different name :: ");
                        break;
                    }

                    ArrayList<Contact> newAddressBook = new ArrayList<>();
                    personDetailsList = newAddressBook;
                    while (flag) {
                        System.out.println("Choose what you want to do: ");
                        System.out.println("1.Add details.\n2.Edit details.\n3.Delete contact.\n4.Check Duplicate Name.\n5.Search Person By City \n6.Exit");
                        int choose1 = sc.nextInt();
                        if (choose1 == 6) {
                            System.out.println("Exited");
                            break;
                        }
                        switch (choose1) {
                            case 1:
                                personDetails.addDetails();
                                break;
                            case 2:
                                personDetails.editDetails();
                                break;
                            case 3:
                                System.out.println("Enter the Contact to be deleted:");
                                String confirm_name = sc.next();
                                boolean listDeleted = personDetails.deleteContact(confirm_name);
                                if (listDeleted) {
                                    System.out.println("Deleted Contact from the List");
                                } else {
                                    System.out.println("List Cannot be Deleted");
                                }
                            case 4:
                                System.out.println("Enter the name you want to check");
                                String enteredName = sc.next();
                                personDetails.duplicateCheck(enteredName);
                            case 5:
                                System.out.println("Enter city name to check the person");
                                String eneterdCityName = sc.next();
                                personDetails.searchbycity(eneterdCityName);
                                break;
                            default:
                                System.out.println("Choose valid option");
                                break;
                        }
                        hashmap.put(addressBook, personDetailsList);
                        System.out.println(hashmap);
                    }
                    break;

                case 2:
                    System.out.println("Enter the name of address book: ");
                    String address_name_old = sc.next();

                    // condition to check whether address book exists or no.
                    if (hashmap.containsKey(address_name_old)) {

                        ArrayList<Contact> old_address_book = new ArrayList<>();
                        personDetailsList = old_address_book;
                        personDetailsList = hashmap.get(address_name_old);
                        while (true) {
                            System.out.println("Choose what you want to do: ");
                            System.out.println("1.Add details.\n2.Edit details.\n3.Delete contact.\n4.Duplicate check.\n5.Exit");
                            int choose1 = sc.nextInt();
                            if (choose1 == 5) {
                                System.out.println("exit");
                                break;
                            }
                            switch (choose1) {
                                case 1:
                                    personDetails.addDetails();
                                    break;
                                case 2:
                                    personDetails.editDetails();
                                    break;
                                case 3:
                                    System.out.println("Enter the Contact to be deleted :: ");
                                    String confirm_name = sc.next();
                                    boolean listDeleted = personDetails.deleteContact(confirm_name);
                                    if (listDeleted) {
                                        System.out.println("Deleted Contact from the List :: ");
                                    } else {
                                        System.out.println("List Can't be Deleted :: ");
                                    }
                                    break;
                                case 4:
                                    System.out.println("Enter first name to check for duplicate");
                                    String enteredName = sc.next();
                                    personDetails.duplicateCheck(enteredName);
                                    break;
                                case 5:
                                    System.out.println("Enter The Name You Want To Search Person In City");
                                    String eneterdCityName = sc.next();
                                    personDetails.searchbycity(eneterdCityName);
                                default:
                                    System.out.println("Choose valid option :: ");
                                    break;
                            }
                            hashmap.put(address_name_old, personDetailsList);
                            System.out.println(hashmap);
                        }
                    } else {
                        System.out.println("Enter valid address book name :: ");
                    }
                    break;

                case 3:
                    System.out.println(hashmap);
                    break;

                default:
                    System.out.println("Enter valid option :: ");
            }
        }
    }
}
