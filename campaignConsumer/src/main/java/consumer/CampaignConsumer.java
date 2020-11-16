package consumer;

import com.rabbitmq.client.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampaignConsumer
{
    private final static String[] CAMPAIGN_SUBSCRIPTIONS = {"Winter campaign", "Spring campaign", "Summer campaign", "Autumn campaign"};

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(CampaignConsumer.class, args);
        subscribe(CAMPAIGN_SUBSCRIPTIONS[3]);
    }

    public static void subscribe(String campaignName) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(campaignName, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) ->
        {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Campaign received: " + message);
        };
        channel.basicConsume(campaignName, true, deliverCallback, consumerTag -> {});
    }

}

