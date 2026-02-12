package org.aman.patientmangement.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="patient" ,groupId = "analytics-service-group")
    public void consumeEvent(byte[] eventData) {

        try {

                PatientEvent patientEvent = PatientEvent.parseFrom(eventData);
                // ... perform amy business related to analytics
                log.info("Received Patient Event : [PatientId={},PatientName={},PatientEmail={}]",
                        patientEvent.getPatientId(),
                        patientEvent.getName(),
                        patientEvent.getEmail());

        } catch (InvalidProtocolBufferException e) {
            log.error("Error parsing patient event: {}", e.getMessage());

        }
    }
}
