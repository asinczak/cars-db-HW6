package pl.akademiaspring.cars.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.akademiaspring.cars.model.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> getCars() {
        String sql = "SELECT * FROM cars";
        return jdbcTemplate.query(sql, new RowMapper<Car>(){
            @Override
            public Car mapRow(ResultSet rs, int i) throws SQLException {
                return new Car(rs.getLong("id"), rs.getString("mark"),
                        rs.getString("model"), rs.getString("color"),
                        rs.getLong("production_year"));

            }
        });
    }

    @Override
    public void addNewCar(Car car) {
        String sql = "INSERT INTO cars VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getId(), car.getMark(), car.getModel(), car.getColor(), car.getProductionYear());
    }

    @Override
    public List<Car> getCarsFromRange(long form, long to) {
        String sql = "SELECT * FROM cars WHERE production_year >= ? AND production_year <= ?";
        return jdbcTemplate.query(sql, new RowMapper<Car>(){
            @Override
            public Car mapRow(ResultSet rs, int i) throws SQLException {
                return new Car(rs.getLong("id"), rs.getString("mark"),
                        rs.getString("model"), rs.getString("color"),
                        rs.getLong("production_year"));

            }
        }, form, to);
    }
}
