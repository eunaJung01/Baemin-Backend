package baemin_backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostUserResponse {

    private final long userId;
    private final String jwt;

}
