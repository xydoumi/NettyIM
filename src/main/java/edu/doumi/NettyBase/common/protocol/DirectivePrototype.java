package edu.doumi.NettyBase.common.protocol;

/**
 *  指令原型
 */
public abstract class DirectivePrototype implements Protocol {
    /**
     * 指令编号
     * @return enum Directive.value
     */
    public abstract byte getDirective();

}
