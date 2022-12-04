package util;

import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static String host = "127.0.0.1";
    private static String port = "5432";
    private static String dbName = "VK_HW7";
    private static String user = "postgres";
    private static String password = "postgres";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + dbName, user, password);
        } catch (SQLException e) {
            System.err.println("Unable to open connection...");
        }
        return null;
    }

}
