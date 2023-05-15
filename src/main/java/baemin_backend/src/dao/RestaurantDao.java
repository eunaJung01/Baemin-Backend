package baemin_backend.src.dao;

import baemin_backend.src.dto.GetCategoryResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RestaurantDao {

    private final JdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetCategoryResponse> getCategories() {
        String query = "select * from category where status='active'";
        return this.jdbcTemplate.query(query,
                (rs, rowNum) -> new GetCategoryResponse(
                        rs.getString("name")
                ));
    }

}
