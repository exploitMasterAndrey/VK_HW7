package db_migration;

import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;

public class DBCreator {
    public static final @NotNull
    String CONNECTION = "jdbc:postgresql://localhost:5432/";
    public static final @NotNull
    String DB_NAME = "VK_HW7";
    public static final @NotNull String USERNAME = "postgres";
    public static final @NotNull String PASSWORD = "postgres";

    public static void init() {
        final Flyway flyway = Flyway
                .configure()
                .cleanDisabled(false)
                .dataSource(CONNECTION + DB_NAME, USERNAME, PASSWORD)
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();
        System.out.println("Migrations applied successfully");
    }
}
