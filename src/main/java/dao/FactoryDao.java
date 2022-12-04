package dao;

import model.Factory;
import org.jetbrains.annotations.NotNull;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import util.ConnectionProvider;

import java.sql.SQLException;

import static generated.Tables.FACTORY;

public class FactoryDao{
    public void create(@NotNull Factory entity) {
        try(var connection = ConnectionProvider.getConnection()){
            var dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            dslContext.newRecord(FACTORY).setName(entity.getName()).setAddress(entity.getAddress()).store();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Factory read(@NotNull String name) {
        Factory factory = null;
        try(var connection = ConnectionProvider.getConnection()) {
            var dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            var res = dslContext.select(FACTORY.NAME, FACTORY.ADDRESS).from(FACTORY).where(FACTORY.NAME.like(name));
            for (var row : res) {
                factory = new Factory(row.value1(), row.value2());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return factory;
    }
}
