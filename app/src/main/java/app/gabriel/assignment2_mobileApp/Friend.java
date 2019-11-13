//Gabriel Lupu c15712195 DT354/year4
package app.gabriel.assignment2_mobileApp;


public class Friend {

    private String name;
    private String phoneNumber;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return name.equals(friend.name) &&
                phoneNumber.equals(friend.phoneNumber) &&
                email.equals(friend.email);
    }
}
