package com.example.listviewdemo;

public class Contact {
    String name;
    String phone;
    boolean icon;

    public Contact(String name, String phone, boolean icon) {
        this.name = name;
        this.phone = phone;
        this.icon = icon;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }
}
