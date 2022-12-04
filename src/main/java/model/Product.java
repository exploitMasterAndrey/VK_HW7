package model;

import io.r2dbc.spi.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private @NotNull Integer id;

    private @NotNull String name;

    private @NotNull String factoryName;

    private @NotNull Integer count;
}
