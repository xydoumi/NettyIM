package edu.doumi.nettyBase.common.directive;

import lombok.Getter;

/**
 *  指令原型
 */
public enum DirectiveType {
    LOGIN((byte)1, LoginDirective.class),
    MESSAGE((byte)2, MessageDirective.class);

    @Getter
    byte directiveId;
    @Getter
    Class<? extends IMDirective> cls;
    /**
     * 指令编号
     * @return enum Directive.value
     */
    DirectiveType(byte directiveId, Class<? extends IMDirective> cls) {
        this.directiveId = directiveId;
        this.cls = cls;
    }

    public static DirectiveType getDirectiveType(byte directiveId){
        for(DirectiveType directive: values()) {
            if(directive.getDirectiveId() == directiveId) {
                return directive;
            }
        }
        return null;
    }
}
