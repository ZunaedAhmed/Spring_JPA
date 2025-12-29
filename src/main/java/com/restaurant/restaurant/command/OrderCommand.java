package com.restaurant.restaurant.command;

import com.restaurant.restaurant.model.OrderFormModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCommand implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Valid
    private OrderFormModel formModel;
}
