package com.patika.invoiceservice.listener;




import com.patika.invoiceservice.dto.request.InvoiceRequest;
import com.patika.invoiceservice.listener.dto.InvoiceDTO;
import com.patika.invoiceservice.entities.Order;
import com.patika.invoiceservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestListener {
    private final Jackson2ObjectMapperBuilder objectMapper = Jackson2ObjectMapperBuilder.json();

    private final InvoiceService invoiceService;


    @RabbitListener(queues = "${rabbitmq.queue}")
    public void listen(Message requestDTO) {
        log.info("Kuyruktan okundu {}", requestDTO);

        try {
            InvoiceDTO invoiceDTO = objectMapper.build().readValue(requestDTO.getBody(), InvoiceDTO.class);
            log.info("Çevrildi {}", invoiceDTO);
            invoiceService.createInvoice(prepareInvoiceRequest(invoiceDTO));

        } catch (Exception e) {
            log.error("Error", e);
        }

    }

    private InvoiceRequest prepareInvoiceRequest(InvoiceDTO invoiceDTO) {
        return InvoiceRequest.builder()
                .order(invoiceDTO.getOrder())
                .build();

    }


}
