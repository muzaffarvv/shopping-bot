package uz.pdp.model;

import lombok.*;
import uz.pdp.base.BaseModel;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Catalog extends BaseModel {
    private String name;
    private UUID parentId;

}
