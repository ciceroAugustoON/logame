package logame.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JdbcClientTest {

    @Test
    void sqlParamsTest1() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("INSERT INTO product(id, name, price) VALUES (1, 'Nintendo 3DS', 499.0)", jdbcClient.sql("INSERT INTO product(id, name, price) VALUES (:id, :name, :price)").params(1, "Nintendo 3DS", 499.0).query());
    }
}
