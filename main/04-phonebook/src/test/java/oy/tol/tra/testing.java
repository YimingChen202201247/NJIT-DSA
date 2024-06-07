package oy.tol.tra;

public class Main {
    public static void main(String[] args) {
        PhoneBook<String, String> hashTablePhoneBook = new PhoneBook<>(new HashTable<>());
        PhoneBook<String, String> bstPhoneBook = new PhoneBook<>(new BST<>());

        hashTablePhoneBook.addEntry("John Doe", "123456789");
        hashTablePhoneBook.addEntry("Jane Doe", "987654321");
        hashTablePhoneBook.addEntry("John Doe", "111222333"); // Duplicate entry

        bstPhoneBook.addEntry("John Doe", "123456789");
        bstPhoneBook.addEntry("Jane Doe", "987654321");
        bstPhoneBook.addEntry("John Doe", "111222333"); // Duplicate entry

        System.out.println("HashTable PhoneBook:");
        System.out.println("John Doe: " + hashTablePhoneBook.getEntry("John Doe"));
        System.out.println("Jane Doe: " + hashTablePhoneBook.getEntry("Jane Doe"));
        System.out.println("Contains John Doe: " + hashTablePhoneBook.containsEntry("John Doe"));
        System.out.println("Size: " + hashTablePhoneBook.getSize());
        System.out.println("Is empty: " + hashTablePhoneBook.isEmpty());

        System.out.println("BST PhoneBook:");
        System.out.println("John Doe: " + bstPhoneBook.getEntry("John Doe"));
        System.out.println("Jane Doe: " + bstPhoneBook.getEntry("Jane Doe"));
        System.out.println("Contains John Doe: " + bstPhoneBook.containsEntry("John Doe"));
        System.out.println("Size: " + bstPhoneBook.getSize());
        System.out.println("Is empty: " + bstPhoneBook.isEmpty());
    }
}