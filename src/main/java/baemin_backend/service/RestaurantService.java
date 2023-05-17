package baemin_backend.service;

import baemin_backend.dto.restaurant.GetCategoriesResponse;
import baemin_backend.dao.RestaurantDao;
import baemin_backend.dto.restaurant.GetRestaurantsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public List<GetCategoriesResponse> getCategories() {
        return restaurantDao.getCategories();
    }

    public List<GetRestaurantsResponse> getRestaurants(int page, double minStar, String search, String sortBy) {
        return restaurantDao.getRestaurants(page, minStar, search, sortBy);
    }

    public List<GetRestaurantsResponse> getRestaurantsByCategory(int page, int categoryId, double minStar, String search, String sortBy) {
        return restaurantDao.getRestaurantsByCategory(page, categoryId, minStar, search, sortBy);
    }

}
