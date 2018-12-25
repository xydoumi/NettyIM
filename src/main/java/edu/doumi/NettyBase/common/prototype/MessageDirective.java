package edu.doumi.NettyBase.common.prototype;

import edu.doumi.NettyBase.common.protocol.DirectivePrototype;
import edu.doumi.nettyIM.model.MessageEntity;
import lombok.Data;

@Data
public class MessageDirective extends DirectivePrototype{
    private byte directiveID = 2;
    private MessageEntity messageEntity;

    @Override
    public byte getDirective() {
        return directiveID;
    }
}
