package uz.pdp.model;

import lombok.*;
import uz.pdp.base.BaseModel;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class User extends BaseModel {
    private String fullName;
    private String username;
    private String password;
    private String phoneNumber;
}
