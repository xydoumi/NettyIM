package edu.doumi.nettyBase.common.directive;

import com.alibaba.fastjson.annotation.JSONField;
import edu.doumi.nettyIM.model.MessageEntity;
import edu.doumi.nettyIM.model.SessionEntity;
import lombok.Getter;
import lombok.Setter;

public class MessageDirective implements IMDirective{

    @JSONField(serialize = false)
    private DirectiveType directiveType;
    @Getter
    @Setter
    private SessionEntity sessionEntity;
    @Getter
    @Setter
    private MessageEntity messageEntity;

    public MessageDirective(){}

    public MessageDirective(SessionEntity sessionEntity, MessageEntity messageEntity){
        directiveType = DirectiveType.MESSAGE;
        this.sessionEntity = sessionEntity;
        this.messageEntity = messageEntity;
    }

    public DirectiveType getDirectiveType() {

        return DirectiveType.MESSAGE;
    }
}
