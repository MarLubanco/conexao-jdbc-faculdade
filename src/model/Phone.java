package model;

public class Phone {

    private Integer phoneId;

    private String numero;

    public Phone() {
    }

    public Phone(String numero) {
        this.numero = numero;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", numero='" + numero + '\'' +
                '}';
    }
}
