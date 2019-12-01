package order.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import order.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class Repository {
    private final DynamoDBMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(Repository.class);

    public Repository() {
        this(new DbMapper().getMapper());
    }

    Repository(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public void save(Order order) {
        TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
        transactionWriteRequest.addPut(order);

        LOGGER.info("Saving order : {}", order);
        mapper.transactionWrite(transactionWriteRequest);
        LOGGER.info("Order saved successfully");
    }

    public Order get(String id) {
        return mapper.load(Order.class, id);
    }
}