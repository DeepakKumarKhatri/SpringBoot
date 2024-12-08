package com.deepaklearning.controllers;

import com.deepaklearning.dto.ProductRequest;
import com.deepaklearning.dto.StripeResponse;
import com.deepaklearning.services.StripeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class StripeController {

    private StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping(value = "/checkout",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
    public ResponseEntity<StripeResponse> checkoutProducts(
            @RequestHeader(value = "Content-Type", required = false) String contentType,
            @RequestHeader(value = "Accept", required = false) String acceptHeader,
            @Validated @RequestBody ProductRequest productRequest) {

        StripeResponse stripeResponse = stripeService.checkoutProducts(productRequest);
        return ResponseEntity.ok(stripeResponse);
    }
}
