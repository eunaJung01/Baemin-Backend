package baemin_backend.src.service;

import baemin_backend.util.response.ResponseStatus;
import baemin_backend.util.response.exception.BaseException;
import baemin_backend.src.dto.restaurant.GetCategoryResponseDto;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    public GetCategoryResponseDto getCategory() throws BaseException {
//        throw new BaseException(ResponseStatus.FAIL);
        throw new BaseException(ResponseStatus.DATABASE_ERROR);
    }

}
