package com.deepaklearning.services;

import com.deepaklearning.dto.ProductRequest;
import com.deepaklearning.dto.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {
    @Value("${stripe.secretKey}")
    private String secretKey;

    public StripeResponse checkoutProducts(ProductRequest productRequest) {
        try {
            // Set the Stripe API key
            Stripe.apiKey = secretKey;

            // Create Product Data
            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData
                            .builder()
                            .setName(productRequest.getProductName())
                            .build();

            // Create Price Data
            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency(productRequest.getCurrency()
                                    != null ? productRequest.getCurrency() : "USD")
                            .setUnitAmount(productRequest.getProductPrice())
                            .setProductData(productData)
                            .build();

            // Create Line Item
            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(productRequest.getQuantity())
                            .setPriceData(priceData)
                            .build();

            // Create Session Parameters
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8080/api/v1/payments/success")
                    .setCancelUrl("http://localhost:8080/api/v1/payments/cancel")
                    .addLineItem(lineItem)
                    .build();

            // Create Stripe Checkout Session
            Session session = Session.create(params);

            // Return Stripe Response
            return StripeResponse.builder()
                    .status("success")
                    .message("Payment session created")
                    .sessionId(session.getId())
                    .sessionURL(session.getUrl())
                    .build();

        } catch (StripeException e) {
            // Log the detailed error
            System.out.println("Stripe Session Creation Error: {}" + e.getMessage() + e);

            // Return error response
            return StripeResponse.builder()
                    .status("error")
                    .message("Failed to create payment session: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Unexpected Error in Stripe Payment: {}" + e.getMessage() + e);

            return StripeResponse.builder()
                    .status("error")
                    .message("An unexpected error occurred: " + e.getMessage())
                    .build();
        }
    }
}