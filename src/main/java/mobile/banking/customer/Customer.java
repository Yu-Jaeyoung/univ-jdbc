package mobile.banking.customer;

import java.util.Objects;

public class Customer {
    private long id;
    private String name;
    private String email;
    private String cellphone;

    public Customer() {
    }

    public Customer(final String name, final String email, final String cellphone) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
    }

    public Customer(final long id, final String name, final String email, final String cellphone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(cellphone, customer.cellphone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, cellphone);
    }
}
