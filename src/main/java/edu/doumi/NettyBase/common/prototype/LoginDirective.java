package edu.doumi.NettyBase.common.prototype;

import edu.doumi.NettyBase.common.protocol.DirectivePrototype;
import lombok.Data;

@Data
public class LoginDirective extends DirectivePrototype {
    private byte directiveID = 1;
    private String username;
    private String password;

    @Override
    public byte getDirective() {
        return directiveID;
    }
}
