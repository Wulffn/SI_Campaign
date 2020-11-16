package dto;

public class Campaign {

    private String campaign, content;

    public Campaign(String campaign, String content) {
        this.campaign = campaign;
        this.content = content;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaign='" + campaign + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
