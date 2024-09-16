package Exercise1;

public class Singers {
    private int id;
    private String name;
    private String address;
    private String dateOfBirth;
    private int numberOfAlbums;

    // Constructors
    public Singers() {}

    public Singers(int id) {
        this.id = id;
    }

    public Singers(int id, String name) {
        this(id);
        this.name = name;
    }

    public Singers(int id, String name, String address) {
        this(id, name);
        this.address = address;
    }

    public Singers(int id, String name, String address, String dateOfBirth) {
        this(id, name, address);
        this.dateOfBirth = dateOfBirth;
    }

    public Singers(int id, String name, String address, String dateOfBirth, int numberOfAlbums) {
        this(id, name, address, dateOfBirth);
        this.numberOfAlbums = numberOfAlbums;
    }

    // Setters and Getters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setNumberOfAlbums(int numberOfAlbums) {
        this.numberOfAlbums = numberOfAlbums;
    }

    public int getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public void setAllValues(int id, String name, String address, String dateOfBirth, int numberOfAlbums) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbums = numberOfAlbums;
    }
}
