package com.javatechie.controller;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.javatechie.grpc.StockResponse;
import com.javatechie.service.StockClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockTradingController {

    private final StockClientService stockClientService;

    public StockTradingController(StockClientService stockClientService) {
        this.stockClientService = stockClientService;
    }

    @GetMapping("/{symbol}")
    public String getStockPrice(@PathVariable String symbol) throws InvalidProtocolBufferException {
        var stockResponse= stockClientService.getStockPrice(symbol);
        // Convert Protobuf object to JSON string
        return JsonFormat.printer().print(stockResponse);
    }
}
