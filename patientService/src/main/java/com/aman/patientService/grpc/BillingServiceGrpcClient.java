package com.aman.patientService.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub BlockingStub;

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:local}") String serverAddress,
            @Value("${billing.service.port:9090}") int serverPort
    ) {
        log.info("Connecting to Billing Service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        BlockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patientId,String name,String email){
        BillingRequest request = BillingRequest
                .newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();

    BillingResponse response = BlockingStub.createBillingAccount(request);
    log.info("Received Billing Response: {}", response);
    return response;
    }
}
