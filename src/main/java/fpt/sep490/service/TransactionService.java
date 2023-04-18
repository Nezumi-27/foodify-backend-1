package fpt.sep490.service;

import fpt.sep490.payload.TransactionDto;
import fpt.sep490.payload.TransactionPageable;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionPageable getAllTransaction(int pageNo, int pageSize, String sortBy, String sortDir);
}
