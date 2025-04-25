package mobile.banking.customer.repository.jdbctemplate;

import mobile.banking.customer.Customer;
import mobile.banking.customer.CustomerSearchCondition;
import mobile.banking.customer.repository.CustomerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCustomerRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO customer (name, email, cellphone) VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {

            PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"id"});
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getCellphone());
            return pstmt;
        }, keyHolder);

        long key = keyHolder.getKey().longValue();
        customer.setId(key);

        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";

        try {
            Customer customer = jdbcTemplate.queryForObject(sql, rowMapper(), id);
            return Optional.of(customer);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, rowMapper());
    }

    private RowMapper<Customer> rowMapper() {
        return ((rs, rowNum) -> new Customer(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("cellphone")
        ));
    }

    @Override
    public int update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, email = ?, cellphone = ? WHERE id = ?";
        return jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getCellphone(), customer.getId());
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        try {
            Customer customer = jdbcTemplate.queryForObject(sql, rowMapper(), email);
            return Optional.of(customer);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> findCustomers(CustomerSearchCondition cond) {
        String sql = "SELECT * FROM customer WHERE 1 = 1";

        List<Object> params = new ArrayList<>();

        if (cond.getName() != null) {
            sql += " AND name LIKE ?";
            params.add("%" + cond.getName() + "%");
        }

        if (cond.getEmail() != null) {
            sql += " AND email LIKE ?";
            params.add("%" + cond.getEmail() + "%");
        }

        if (cond.getCellphone() != null) {
            sql += " AND cellphone LIKE ?";
            params.add("%" + cond.getCellphone() + "%");
        }

        return jdbcTemplate.query(sql, rowMapper(), params.toArray());
    }
}
