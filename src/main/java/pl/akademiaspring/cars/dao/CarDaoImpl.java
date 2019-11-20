package pl.akademiaspring.cars.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.akademiaspring.cars.model.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> getCars() {
        String sql = "SELECT * FROM cars_list";
        return jdbcTemplate.query(sql, new RowMapper<Car>(){
            @Override
            public Car mapRow(ResultSet rs, int i) throws SQLException {
                return new Car(rs.getLong("id"),
                        rs.getString("mark"),
                        rs.getString("model"), rs.getString("color"),
                        rs.getInt("production_year"));

            }
        });
    }

    @Override
    public void addNewCar(Car car) {
        String sql = "INSERT INTO cars_list (mark, model, color, production_year) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, car.getMark());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getColor());
            ps.setLong(4, car.getProductionYear());
            return ps;
        }, keyHolder);

    }

    @Override
    public List<Car> getCarsFromRange(long form, long to) {
        String sql = "SELECT * FROM cars_list WHERE production_year >= ? AND production_year <= ?";
        return jdbcTemplate.query(sql, new RowMapper<Car>(){
            @Override
            public Car mapRow(ResultSet rs, int i) throws SQLException {
                return new Car(rs.getLong("id"),
                        rs.getString("mark"),
                        rs.getString("model"), rs.getString("color"),
                        rs.getInt("production_year"));

            }
        }, form, to);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createTable(){
       String sql = "CREATE TABLE IF NOT EXISTS cars_list (\n" +
               "    id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
               "    mark varchar(100),\n" +
               "    model varchar(100),\n" +
               "    color varchar(100),\n" +
               "    production_year int(10))";
       jdbcTemplate.update(sql);

    }
}
