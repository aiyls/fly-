package cn.aiyls.fly.im.subpubmq;

public enum ChannelEnum {

    WEBSOCKET("websocket");

    private final String topic;

    ChannelEnum(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

}
