package baemin_backend.dao;

import baemin_backend.dto.user.PostPutUserRequest;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Map;

//@Repository
public class UserDaoV3 {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public UserDaoV3(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("user")
                .usingGeneratedKeyColumns("user_id");
    }

    public boolean hasDuplicateEmail(String email) {
        String sql = "select exists(select email from user where email=:email and status in ('active', 'dormant'))";
        Map<String, Object> param = Map.of("email", email);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public boolean hasDuplicateNickName(String nickname) {
        String sql = "select exists(select email from user where nickname=:nickname and status in ('active', 'dormant'))";
        Map<String, Object> param = Map.of("nickname", nickname);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public long createUserV3(PostPutUserRequest postUserRequest) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserRequest);
        Number key = jdbcInsert.executeAndReturnKey(param);
        return key.longValue();
    }

}
