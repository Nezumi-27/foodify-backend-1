package fpt.sep490.controller;

import fpt.sep490.payload.TransactionDto;
import fpt.sep490.payload.TransactionPageable;
import fpt.sep490.service.TransactionService;
import fpt.sep490.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("CRUD APIs for User Resources")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation("Create new Transation")
    @PostMapping()
    public ResponseEntity<TransactionDto> createNewTransaction(@RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

    @ApiOperation("Get All Transations")
    @GetMapping()
    public ResponseEntity<TransactionPageable> getAllTransaction(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createdTime", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ){
        return ResponseEntity.ok(transactionService.getAllTransaction(pageNo, pageSize, sortBy, sortDir));
    }
}
