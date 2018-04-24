package com.epam.bikeRetail.entity;

import com.epam.bikeRetail.dao.Identifiable;

import java.math.BigDecimal;

/**
 * Class saves information from BD to application
 *
 * @author Stepan Menchytsky
 */
public class User implements Identifiable {

    private int id;
    private String name;
    private String lastName;
    private String login;
    private String password;
    private UserRole userRole;
    private BigDecimal balance;
    private int rentStatus;
    private int activeStatus;

    public User() {
    }

    public User(int id, String name, String lastName, String login, String password, UserRole userRole, BigDecimal balance, int rentStatus, int activeStatus) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
        this.balance = balance;
        this.rentStatus = rentStatus;
        this.activeStatus = activeStatus;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(int rentStatus) {
        this.rentStatus = rentStatus;
    }

    public int getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", balance=" + balance +
                ", rentStatus=" + rentStatus +
                ", activeStatus=" + activeStatus +
                '}';
    }
}
