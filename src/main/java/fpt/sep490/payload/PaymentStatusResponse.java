package fpt.sep490.payload;

public record PaymentStatusResponse(
        String returnMessage, String subReturnMessage, Boolean isProcessing, Integer httpStatusCode) {
}
