package baemin_backend.src.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Setter
@NoArgsConstructor
public class PostUserRequest {

    @Email
    @NotBlank(message = "email: {NotBlank}")
    @Length(max = 50, message = "email: 최대 {max}자리까지 가능합니다")
    private String email;

    @NotBlank(message = "password: {NotBlank}")
    @Length(min = 8, max = 20,
            message = "password: 최소 {min}자리 ~ 최대 {max}자리까지 가능합니다")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
            message = "password: 대문자, 소문자, 특수문자가 적어도 하나씩은 있어야 합니다")
    private String password;

    @NotBlank(message = "phoneNumber: {NotBlank}")
    @Length(max = 20, message = "phoneNumber: 최대 {max}자리까지 가능합니다")
    private String phoneNumber;

    @Nullable
    @Length(max = 25, message = "nickName: 최대 {max}자리까지 가능합니다")
    private String nickName;

    @Nullable
    private String profileImage;

}