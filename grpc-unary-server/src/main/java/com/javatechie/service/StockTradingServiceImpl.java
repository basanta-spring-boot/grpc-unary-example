package com.javatechie.service;

import com.javatechie.entity.Stock;
import com.javatechie.grpc.StockRequest;
import com.javatechie.grpc.StockResponse;
import com.javatechie.grpc.StockTradingServiceGrpc;
import com.javatechie.repository.StockRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class StockTradingServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    private final StockRepository stockRepository;

    public StockTradingServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void getStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        String stockSymbol = request.getStockSymbol();
        Optional<Stock> stock = stockRepository.findByStockSymbol(stockSymbol);

        if (stock.isPresent()) {
            Stock stockEntity = stock.get();
            StockResponse response = StockResponse.newBuilder()
                    .setStockSymbol(stockEntity.getStockSymbol())
                    .setPrice(stockEntity.getPrice())
                    .setTimestamp(stockEntity.getLastUpdated() != null ? stockEntity.getLastUpdated().toString() : "N/A")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Stock " + stockSymbol + " is not available in system")
                            .asRuntimeException()
            );
        }
    }
}
