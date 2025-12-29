package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.command.OrderCommand;
import com.restaurant.restaurant.entity.OrderEntity;
import com.restaurant.restaurant.helper.OrderHelper;
import com.restaurant.restaurant.model.OrderFormModel;
import com.restaurant.restaurant.service.OrderService;
import com.restaurant.restaurant.validator.OrderValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.restaurant.restaurant.utils.Constants.DEFAULT_DATE_FORMAT;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
@SessionAttributes(names = {
        OrderController.COMMAND_ORDER
})
public class OrderController {
    public static final String COMMAND_ORDER = "orderCommand";
    private final OrderService orderService;
    private final OrderHelper orderHelper;
    private final OrderValidator validator;

    @InitBinder(COMMAND_ORDER)
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(
                String.class,
                new StringTrimmerEditor(true)
        );
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(
                        new SimpleDateFormat(DEFAULT_DATE_FORMAT),
                        true
                )
        );
        binder.addValidators(validator);
    }

    @ModelAttribute(COMMAND_ORDER)
    public OrderCommand orderCommand() {
        return new OrderCommand(new OrderFormModel());
    }

    @GetMapping
    public List<OrderEntity> list() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> get(@PathVariable Long id) {
        return orderService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        OrderFormModel orderForm = new OrderFormModel();
        orderHelper.newOrder(model, orderForm);
        return "order_form";
    }

    @PostMapping("/new")
    public String createOrder(
            @ModelAttribute(COMMAND_ORDER) @Valid OrderCommand command,
            BindingResult bindingResult,
            Model model,
            SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            orderHelper.createOrder(model);
            return "order_form";
        }
        orderService.doSave(command.getFormModel());
        sessionStatus.setComplete();
        return "redirect:/orders";
    }
}
