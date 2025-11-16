package uz.pdp.model;

import lombok.*;
import uz.pdp.base.BaseModel;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class Like extends BaseModel {

    private UUID objectId;
    private UUID userId;
}
