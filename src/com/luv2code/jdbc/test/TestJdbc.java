package com.luv2code.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSl=false&serverTimeZone=Europe/Paris";
        String user = "hbstudent";
        String password = "hbstudent";

        try {
            System.out.println("Connecting do DB");

            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            System.out.println("Connection successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
