package com.javatechie.service;

import com.javatechie.grpc.StockRequest;
import com.javatechie.grpc.StockResponse;
import com.javatechie.grpc.StockTradingServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {

   @GrpcClient("stockService") // This should match the client name in application.yml
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub stockServiceStub;

    public StockResponse getStockPrice(String stockSymbol) {
        StockRequest request = StockRequest.newBuilder().setStockSymbol(stockSymbol).build();
        return stockServiceStub.getStockPrice(request);
    }


}
