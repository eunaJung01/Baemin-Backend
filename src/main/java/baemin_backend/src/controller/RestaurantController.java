package baemin_backend.src.controller;

import baemin_backend.common.response.BaseResponse;
import baemin_backend.src.dto.GetCategoryResponse;
import baemin_backend.src.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/categories")
    public BaseResponse<List<GetCategoryResponse>> getCategories() {
        return new BaseResponse<>(restaurantService.getCategories());
    }

}
