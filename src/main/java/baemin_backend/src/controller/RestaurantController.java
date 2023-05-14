package baemin_backend.src.controller;

import baemin_backend.util.response.BaseResponse;
import baemin_backend.util.response.exception.BaseException;
import baemin_backend.src.dto.restaurant.GetCategoryResponseDto;
import baemin_backend.src.service.RestaurantService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @ResponseBody
    @GetMapping("/categories/{temp}")
    public BaseResponse<GetCategoryResponseDto> getCategory(@PathVariable @Nullable String temp) throws BaseException {
        if (temp.equals("yee")) {
            throw new IllegalArgumentException();
        }

        GetCategoryResponseDto categoryResponseDto = restaurantService.getCategory();
        return new BaseResponse<>(categoryResponseDto);
    }

}
