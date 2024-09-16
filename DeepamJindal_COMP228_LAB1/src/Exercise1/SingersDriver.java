package Exercise1;

public class SingersDriver {
    public static void main(String[] args) {

        Singers singer1 = new Singers();

        System.out.println("Default Values:");
        System.out.println("ID: " + singer1.getId());
        System.out.println("Name: " + singer1.getName());
        System.out.println("Address: " + singer1.getAddress());
        System.out.println("Date of Birth: " + singer1.getDateOfBirth());
        System.out.println("Number of Albums: " + singer1.getNumberOfAlbums());

        singer1.setId(1);
        singer1.setName("Deepam Jindal");
        singer1.setAddress("Toronto, Ontario, Canada");
        singer1.setDateOfBirth("22 March 2004");
        singer1.setNumberOfAlbums(5);

        System.out.println("\nUpdated Values:");
        System.out.println("ID: " + singer1.getId());
        System.out.println("Name: " + singer1.getName());
        System.out.println("Address: " + singer1.getAddress());
        System.out.println("Date of Birth: " + singer1.getDateOfBirth());
        System.out.println("Number of Albums: " + singer1.getNumberOfAlbums());
    }
}

