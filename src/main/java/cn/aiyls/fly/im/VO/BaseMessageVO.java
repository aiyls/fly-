package cn.aiyls.fly.im.VO;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/5
 * @Desc:
 */
public interface BaseMessageVO {

    default void handleMessage() {
        System.out.println("该消息处理类没有 handleMessage 的实现");
    }

    Long fetchReceiver();
}
