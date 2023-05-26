package baemin_backend.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {

    private String email;
    private String phoneNumber;
    private String nickname;
    private String profileImage;
    private String status;

}
