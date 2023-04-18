package fpt.sep490.service.impl;

import fpt.sep490.payload.MerchantStatus;
import fpt.sep490.service.MerchantStatusService;
import org.springframework.stereotype.Service;

@Service
public class MerchantStatusServiceImpl implements MerchantStatusService {
    @Override
    public MerchantStatus getStatusOrder(String orderId) {
        return new MerchantStatus("1", "DELIVERED");
    }
}
