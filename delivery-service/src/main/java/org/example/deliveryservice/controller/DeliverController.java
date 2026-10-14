package org.example.deliveryservice.controller;


import lombok.AllArgsConstructor;
import org.example.deliveryservice.service.DeliverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/deliver")
public class DeliverController {
    private static final Logger log = LoggerFactory.getLogger(DeliverController.class);
    private final DeliverService deliverService;

    @PostMapping("/{id}")
    public org.example.library.order.OrderDto startingDeliver(@PathVariable("id") Long id){
        log.info("inside startingDeliver");
        return deliverService.startingDeliver(id);
    }
}
