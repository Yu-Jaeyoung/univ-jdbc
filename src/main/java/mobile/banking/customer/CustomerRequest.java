package mobile.banking.customer;

public class CustomerRequest {
    private String name;
    private String email;
    private String cellphone;

    public CustomerRequest() {
    }

    public CustomerRequest(String name, String email, String cellphone) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphone() {
        return cellphone;
    }
}
