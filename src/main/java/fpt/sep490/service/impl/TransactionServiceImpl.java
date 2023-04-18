package fpt.sep490.service.impl;

import fpt.sep490.entity.Transaction;
import fpt.sep490.payload.PageableDto;
import fpt.sep490.payload.TransactionDto;
import fpt.sep490.payload.TransactionPageable;
import fpt.sep490.repository.TransactionRepository;
import fpt.sep490.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private ModelMapper mapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ModelMapper mapper) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(transactionDto.getId());
        newTransaction.setProductCost(transactionDto.getProductCost());
        newTransaction.setShippingCost(transactionDto.getShippingCost());
        newTransaction.setTotal(transactionDto.getTotal());
        newTransaction.setUserFullName(transactionDto.getUserFullName());

        Transaction savedTransaction = transactionRepository.save(newTransaction);
        return mapper.map(savedTransaction, TransactionDto.class);
    }

    @Override
    public TransactionPageable getAllTransaction(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        List<Transaction> lists = transactions.getContent();
        List<TransactionDto> content = lists.stream().map(transaction -> mapper.map(transaction, TransactionDto.class)).collect(Collectors.toList());

        PageableDto pageableDto = new PageableDto();
        pageableDto.setPageNo(transactions.getNumber());
        pageableDto.setPageSize(transactions.getSize());
        pageableDto.setTotalElements(transactions.getTotalElements());
        pageableDto.setTotalPages(transactions.getTotalPages());
        pageableDto.setLast(transactions.isLast());

        TransactionPageable responses = new TransactionPageable();
        responses.setTransactions(content);
        responses.setPage(pageableDto);
        return responses;
    }
}
