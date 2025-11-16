package uz.pdp.base;

import lombok.*;

import java.util.UUID;
@EqualsAndHashCode
@Getter
public abstract class BaseModel {

    private final UUID id;
    @Setter
    private boolean active = true;

    public BaseModel() {
        this.id = UUID.randomUUID();
    }
}
