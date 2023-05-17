package baemin_backend.dao;

import baemin_backend.dto.GetCategoriesResponse;
import baemin_backend.dto.GetRestaurantsResponse;
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

    public List<GetCategoriesResponse> getCategories() {
        String query = "select * from category where status='active'";
        return this.jdbcTemplate.query(query,
                (rs, rowNum) -> new GetCategoriesResponse(
                        rs.getString("name")
                ));
    }

    // TODO: paging, sortBy 구현 필요
    public List<GetRestaurantsResponse> getRestaurants(double minStar, double star, String search, String sortBy) {
        String query = "select name, address, heart_count, star_rate from restaurant where status='active'";
        return this.jdbcTemplate.query(query,
                (rs, rowNum) -> new GetRestaurantsResponse(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getInt("heart_count"),
                        rs.getDouble("star_rate")
                ));
    }

    // TODO: paging, sortBy, categoryId로 탐색 구현 필요
    public List<GetRestaurantsResponse> getRestaurantsByCategory(double minStar, int categoryId, double star, String search, String sortBy) {
        String query = "select name, address, heart_count, star_rate from restaurant where status='active'";
        return this.jdbcTemplate.query(query,
                (rs, rowNum) -> new GetRestaurantsResponse(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getInt("heart_count"),
                        rs.getDouble("star_rate")
                ));
    }

}
