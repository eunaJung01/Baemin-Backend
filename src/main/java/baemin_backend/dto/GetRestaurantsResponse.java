package baemin_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class GetRestaurantsResponse {

    private String name;
    private String address;
    private int heartCount;
    private double starRate;

}
