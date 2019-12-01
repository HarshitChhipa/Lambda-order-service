package order.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@DynamoDBDocument
public class Item {

    @DynamoDBAttribute
    private String id;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private String price;

    @DynamoDBAttribute
    private String quantity;
}