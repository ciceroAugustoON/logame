package logame.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JdbcClientTest {

    @Test
    void testSqlParams1() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("INSERT INTO product VALUES (1, :name, :price)", jdbcClient.sql("INSERT INTO product VALUES (:id, :name, :price)").param(0, 1).query());
    }
    @Test
    void testSqlParams2() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("SELECT * FROM games WHERE id = 5", jdbcClient.sql("SELECT * FROM games WHERE id = :id").param(0, 5).query());
    }
    @Test
    void testSqlParams3() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("UPDATE users SET name = 'John Doe' WHERE id = 2", jdbcClient.sql("UPDATE users SET name = :name WHERE id = :id").param(0, "John Doe").param(1, 2).query());
    }

    @Test
    void testSqlParams4() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("DELETE FROM orders WHERE order_id = 10", jdbcClient.sql("DELETE FROM orders WHERE order_id = :order_id").param(0, 10).query());
    }

    @Test
    void testSqlParams5() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("INSERT INTO employees (id, name, department) VALUES (3, 'Jane Smith', 'HR')", jdbcClient.sql("INSERT INTO employees (id, name, department) VALUES (:id, :name, :department)").param(0, 3).param(1, "Jane Smith").param(2, "HR").query());
    }

    @Test
    void testSqlParams6() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("SELECT * FROM products WHERE category = 'Electronics' AND price < 1000", jdbcClient.sql("SELECT * FROM products WHERE category = :category AND price < :price").param(0, "Electronics").param(1, 1000).query());
    }

    @Test
    void testSqlParams7() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("INSERT INTO sales (id, product_id, quantity, total_price) VALUES (7, 101, 3, 299.99)", jdbcClient.sql("INSERT INTO sales (id, product_id, quantity, total_price) VALUES (:id, :product_id, :quantity, :total_price)").param(0, 7).param(1, 101).param(2, 3).param(3, 299.99).query());
    }

    @Test
    void testSqlParams8() {
        JdbcClient jdbcClient = JdbcClient.create();
        assertEquals("SELECT * FROM customers WHERE last_name = 'Doe' ORDER BY first_name ASC", jdbcClient.sql("SELECT * FROM customers WHERE last_name = :last_name ORDER BY first_name ASC").param(0, "Doe").query());
    }
    
}
