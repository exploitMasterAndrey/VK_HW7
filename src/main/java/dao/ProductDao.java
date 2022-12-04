package dao;

import model.Product;
import org.jetbrains.annotations.NotNull;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import util.ConnectionProvider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static generated.Tables.PRODUCT;

public class ProductDao {
    public void create(@NotNull Product entity) {
        try(var connection = ConnectionProvider.getConnection()) {
            var dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            dslContext.newRecord(PRODUCT).setName(entity.getName()).setFactoryName(entity.getFactoryName()).setCount(entity.getCount()).store();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();

        try(var connection = ConnectionProvider.getConnection()) {
            var dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            var res = dslContext.select(PRODUCT.ID, PRODUCT.NAME, PRODUCT.FACTORY_NAME, PRODUCT.COUNT).from(PRODUCT);
            for (var row : res) {
                productList.add(new Product(row.value1(), row.value2(), row.value3(), row.value4()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productList;
    }
}
