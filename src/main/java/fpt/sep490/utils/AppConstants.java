package fpt.sep490.utils;

public class AppConstants {
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    public static final String DEFAULT_ADDRESS_PAGE_SIZE = "5";
    public static final String DEFAULT_SHOP_PAGE_SIZE = "10";
    public static final String DEFAULT_SHIPPER_PAGE_SIZE = "10";
    public static final String DEFAULT_PRODUCT_PAGE_SIZE = "20";
    public static final String DEFAULT_ORDER_PAGE_SIZE = "10";
    public static final String DEFAULT_USER_PAGE_SIZE = "10";

    public enum ORDER_STATUS {
        AWAITING, CONFIRMED, SHIPPING, COMPLETED, CANCELED
    }
}
