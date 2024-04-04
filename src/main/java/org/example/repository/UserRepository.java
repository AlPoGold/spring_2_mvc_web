package org.example.repository;

import org.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName,lastName) VALUES ( ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return  user;
    }

    //TODO public void deleteById(int id)

    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    //TODO
    public void updateById(int id, User changedUser) {
        User user = findById(id);
        if (user != null) {
            String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
            jdbc.update(sql, changedUser.getFirstName(), changedUser.getLastName(), id);
        } else {
            System.out.println("No user with such id");
        }

    }


    public User findById(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        RowMapper<User> userRowMapper = (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            return user;
        };
        return jdbc.queryForObject(sql, userRowMapper, id);

    }
}
