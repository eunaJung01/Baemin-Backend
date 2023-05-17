package baemin_backend.controller;

import baemin_backend.common.response.BaseResponse;
import baemin_backend.dto.GetCategoriesResponse;
import baemin_backend.dto.GetRestaurantsResponse;
import baemin_backend.service.RestaurantService;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/categories")
    public BaseResponse<List<GetCategoriesResponse>> getCategories() {
        return new BaseResponse<>(restaurantService.getCategories());
    }

    @GetMapping("/{page}")
    public BaseResponse<List<GetRestaurantsResponse>> getRestaurants(
            @Min(1) @PathVariable int page,
            @DecimalMin("0.0") @DecimalMax("5.0") @RequestParam(required = false, defaultValue = "0.0") double minStar,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "default") String sortBy) {

        // TODO: sortBy 검증 필요 (default, order, star, heart)

        return new BaseResponse<>(restaurantService.getRestaurants(page, minStar, search, sortBy));
    }

    @GetMapping("/{categoryId}/{page}")
    public BaseResponse<List<GetRestaurantsResponse>> getRestaurantsByCategory(
            @Range(min = 0, max = 8) @PathVariable int categoryId,
            @Min(1) @PathVariable int page,
            @DecimalMin("0.0") @DecimalMax("5.0") @RequestParam(required = false, defaultValue = "0.0") double minStar,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "default") String sortBy) {

        // TODO: sortBy 검증 필요 (default, order, star, heart)

        return new BaseResponse<>(restaurantService.getRestaurantsByCategory(page, categoryId, minStar, search, sortBy));
    }

}
