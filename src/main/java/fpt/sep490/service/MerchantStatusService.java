package fpt.sep490.service;

import fpt.sep490.payload.MerchantStatus;

public interface MerchantStatusService {
    MerchantStatus getStatusOrder(String orderId);
}
