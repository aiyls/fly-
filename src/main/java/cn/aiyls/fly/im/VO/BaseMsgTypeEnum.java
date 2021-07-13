package cn.aiyls.fly.im.vo;

public enum BaseMsgTypeEnum {

    TEXT("text"),
    NOTICE("notice"),
    PING("ping");

    private final String type;

    BaseMsgTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
