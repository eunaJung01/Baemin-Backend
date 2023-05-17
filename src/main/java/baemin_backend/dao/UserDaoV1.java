package baemin_backend.dao;

import baemin_backend.dto.user.PostUserRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Objects;

//@Repository
public class UserDaoV1 {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoV1(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean hasDuplicateEmail(String email) {
        String query = "select exists(select email from user where email = ? and status in ('active', 'dormant'))";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(query, boolean.class, email));
    }

    public boolean hasDuplicateNickName(String nickname) {
        String query = "select exists(select nickname from user where nickname = ? and status in ('active', 'dormant'))";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(query, boolean.class, nickname));
    }

    public long createUserV1(PostUserRequest postUserRequest) {
        String query = "insert into user(email, password, phone_number, nickname, profile_image) values(?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setString(1, postUserRequest.getEmail());
            ps.setString(2, postUserRequest.getPassword());
            ps.setString(3, postUserRequest.getPhoneNumber());
            ps.setString(4, postUserRequest.getNickname());
            ps.setString(5, postUserRequest.getProfileImage());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

}