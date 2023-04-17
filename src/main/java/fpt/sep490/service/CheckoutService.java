package fpt.sep490.service;

import fpt.sep490.payload.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CheckoutService {

    MerchantResponse createMerchant(List<ItemDto> item, MerchantDto merchant) throws IOException;
    PaymentStatusResponse statusPayment() throws URISyntaxException, IOException;
}
