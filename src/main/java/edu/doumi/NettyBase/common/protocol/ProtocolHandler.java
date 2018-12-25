package edu.doumi.NettyBase.common.protocol;

import io.netty.buffer.ByteBuf;

enum ProtocolHandler{
    JSONINSTANCE(SerializerContext.build(new JsonSerializer()));
    private SerializerContext serializerContext;

    ProtocolHandler(SerializerContext serializerContext){
        this.serializerContext = serializerContext;
    }
    public void writeToBuf(ByteBuf buf, DirectivePrototype proto){
        // 放入魔数
        buf.writeInt(proto.MAGIC_NUMBER);
        // 放入版本序列号
        buf.writeByte(proto.version);
        // 放入序列化算法
        buf.writeByte(serializerContext.getSerializerAlgorithm());
        // 放入内容长度
        buf.writeInt(serializerContext.serialize(proto).length);
        // 放入内容
        buf.writeBytes(serializerContext.serialize(proto));
    }

    public DirectivePrototype readBuf(ByteBuf in){
        // 跳过魔数4、版本1、序列化1
        in.skipBytes(6);
        int length = in.readInt();
        byte[] context = new byte[length];
        in.readBytes(context);
        return serializerContext.deserialize(DirectivePrototype.
                class, context);
    }
}
