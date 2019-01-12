package edu.doumi.nettyBase.common.directive;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

public class LoginDirective implements IMDirective{

    @JSONField(serialize = false)
    private DirectiveType directiveType;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

    public LoginDirective(String username, String password) {
        directiveType = DirectiveType.LOGIN;
        this.username = username;
        this.password = password;
    }

    @Override
    public DirectiveType getDirectiveType() {
        return DirectiveType.LOGIN;
    }
}
