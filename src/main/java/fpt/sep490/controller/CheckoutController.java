package fpt.sep490.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import fpt.sep490.payload.*;
import fpt.sep490.service.CheckoutService;
import fpt.sep490.service.MerchantStatusService;
import org.springframework.web.bind.annotation.*;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String key2 = "kLtgPl8HHhfvMuDHPwKfgfsY4Ydm9eIz";
    private Mac HmacSHA256;

    private CheckoutService checkoutService;

    private MerchantStatusService merchantStatusService;

    public CheckoutController(CheckoutService checkoutService, MerchantStatusService merchantStatusService) throws NoSuchAlgorithmException, InvalidKeyException {
        this.checkoutService = checkoutService;
        this.merchantStatusService = merchantStatusService;

        HmacSHA256 = Mac.getInstance("HmacSHA256");
        HmacSHA256.init(new SecretKeySpec(key2.getBytes(), "HmacSHA256"));
    }

    @PostMapping("/purchase")
    public MerchantResponse createOrder(@RequestBody String jsonOrder) throws IOException {
        JSONObject input = new JSONObject(jsonOrder);
        JSONArray itemsJson = input.getJSONArray("_items");
        JSONObject orderInfo = input.getJSONObject("_orderInfo");

        List<ItemDto> items = new ArrayList<>();
        for (int i = 0; i < itemsJson.length(); i++) {
            ItemDto item = new ItemDto();

            item.setName(itemsJson.getJSONObject(i).getString("_name"));
            item.setQuantity(itemsJson.getJSONObject(i).getString("_price"));
            item.setPrice(itemsJson.getJSONObject(i).getString("_quantity"));

            items.add(item);
        }

        MerchantDto order = MerchantDto.builder()
                .appUserName(orderInfo.getString("_appUserName"))
                .amount(orderInfo.getString("_amount"))
                .orderId(orderInfo.getString("_orderId")).build();
        return this.checkoutService.createMerchant(items, order);
    }

    @GetMapping("/payment-status")
    public PaymentStatusResponse paymentStatus() throws URISyntaxException, IOException {
        return this.checkoutService.statusPayment();
    }

    @GetMapping("/purchase/success/{orderId}")
    public MerchantStatus getOrderStatus(@PathVariable String orderId) {
        return merchantStatusService.getStatusOrder(orderId);
    }
}
