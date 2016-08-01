package com.example.owner.betterthanmal;

/**
 * Created by Krishna N. Deoram on 2016-07-26.
 * Gumi is love. Gumi is life.
 */

public class AdminObject {

    private String id, firstname, lastname, tableName, insertStatement, time, email, password, role, type;

    public AdminObject(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return id;
    }

    public AdminObject setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public AdminObject setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public AdminObject setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public AdminObject setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getInsertStatement() {
        return insertStatement;
    }

    public AdminObject setInsertStatement(String insertStatement) {
        this.insertStatement = insertStatement;
        return this;
    }

    public String getTime() {
        return time;
    }

    public AdminObject setTime(String time) {
        this.time = time;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdminObject setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminObject setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AdminObject setRole(String role) {
        this.role = role;
        return this;
    }
}
