package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.base.BaseModel;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int year;;
    private UUID categoryId;
}
