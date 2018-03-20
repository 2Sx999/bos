package cn.porkchop.crm.pojo;

public class Customer {
    private int id;
    private String name;
    private String station;
    private String telephone;
    private String address;
    private String decidedzoneId;

    public Customer() {
    }

    public Customer(int id, String name, String station, String telephone, String address, String decidedzoneId) {
        this.id = id;
        this.name = name;
        this.station = station;
        this.telephone = telephone;
        this.address = address;
        this.decidedzoneId = decidedzoneId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDecidedzoneId() {
        return decidedzoneId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", decidedzoneId='" + decidedzoneId + '\'' +
                '}';
    }

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }
}
