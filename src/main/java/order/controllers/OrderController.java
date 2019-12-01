package order.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import order.models.Order;
import order.repository.Repository;
import order.requests.CreateOrderRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller("/v1/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private Repository repository;

    @Post
    public HttpResponse createOrder(@Body CreateOrderRequest orderRequest) {
        LOGGER.info("create order request received: {}", orderRequest);
        ModelMapper modelMapper = getModelMapper();

        Order order = modelMapper.map(orderRequest, Order.class);
        repository.save(order);
        return HttpResponse.created(order);
    }

    @Get(value = "/{orderId}")
    public HttpResponse getOrder(@PathVariable String orderId) {
        LOGGER.info("Getting order for id: {}", orderId);

        Order order = repository.get(orderId);
        return HttpResponse.ok(order);
    }

    private ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

}
