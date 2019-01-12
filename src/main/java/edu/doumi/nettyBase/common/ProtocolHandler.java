package edu.doumi.nettyBase.common;

import edu.doumi.nettyBase.common.directive.DirectiveType;
import edu.doumi.nettyBase.common.directive.IMDirective;
import edu.doumi.nettyBase.common.protocol.Protocol;
import edu.doumi.nettyBase.common.serializer.JsonSerializer;
import edu.doumi.nettyBase.common.serializer.SerializerContext;
import io.netty.buffer.ByteBuf;

enum ProtocolHandler{
    JSON_INSTANCE(SerializerContext.build(new JsonSerializer()));
    private SerializerContext serializerContext;

    ProtocolHandler(SerializerContext serializerContext){
        this.serializerContext = serializerContext;
    }

    public void writeToBuf(ByteBuf buf, IMDirective directive){
        // 放入魔数
        buf.writeInt(Protocol.MAGIC_NUMBER);
        // 放入版本序列号
        buf.writeByte(Protocol.version);
        // 放入序列化算法
        buf.writeByte(serializerContext.getSerializerAlgorithm());
        // 放入指令
        buf.writeByte(directive.getDirectiveType().getDirectiveId());
        // 放入内容长度
        buf.writeInt(serializerContext.serialize(directive).length);
        // 放入内容
        buf.writeBytes(serializerContext.serialize(directive));
    }

    public IMDirective readBuf(ByteBuf in){
        // 重置readIndex
        in.resetReaderIndex();
        // 跳过魔数4、版本1、序列化1
        in.skipBytes(6);
        byte directiveId = in.readByte();
        DirectiveType requestType = DirectiveType.getDirectiveType(directiveId);
        int length = in.readInt();
        byte[] context = new byte[length];
        in.readBytes(context);
        return serializerContext.deserialize(requestType.getCls(), context);
    }
}
