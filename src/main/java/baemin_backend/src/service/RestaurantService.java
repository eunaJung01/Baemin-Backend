package baemin_backend.src.service;

import baemin_backend.src.dao.RestaurantDao;
import baemin_backend.src.dto.GetCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDao restaurantDao;

    public List<GetCategoryResponse> getCategories() {
        return restaurantDao.getCategories();
    }

}
