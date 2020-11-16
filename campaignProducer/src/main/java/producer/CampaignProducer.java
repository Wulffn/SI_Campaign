package producer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dto.Campaign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CampaignProducer
{
    private static List<Campaign> campaigns;

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(CampaignProducer.class, args);
        setup();
        produce(getRandomCampaign());
    }

    public static void produce(Campaign campaign) throws Exception
    {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            System.out.println("Sending " + campaign.getCampaign());
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel())
            {
                channel.queueDeclare(campaign.getCampaign(), false, false, false, null);
                channel.basicPublish("", campaign.getCampaign(), null, campaign.getContent().getBytes("UTF-8"));
            }
    }

    private static void setup() {
        campaigns = new ArrayList<>();
        campaigns.add(new Campaign("Winter campaign", "Some winter campaign text"));
        campaigns.add(new Campaign("Spring campaign", "Some spring campaign text"));
        campaigns.add(new Campaign("Summer campaign", "Some summer campaign text"));
        campaigns.add(new Campaign("Autumn campaign", "Some autumn campaign text"));
    }

    private static Campaign getRandomCampaign() {
        int i = new Random().nextInt(campaigns.size());
        return campaigns.get(i);
    }
}