package org.example.deliveryservice.controller;


import lombok.AllArgsConstructor;
import org.example.deliveryservice.service.DeliverService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/deliver")
public class DeliverController {
    private final DeliverService deliverService;

    @PostMapping("/{id}")
    public org.example.library.order.OrderDto startingDeliver(@PathVariable("id") Long id){
        return deliverService.startingDeliver(id);
    }
}
