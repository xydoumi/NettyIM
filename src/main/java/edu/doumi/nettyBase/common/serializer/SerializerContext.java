package edu.doumi.nettyBase.common.serializer;

public class SerializerContext {
    private SerializerStrategy serializerStrategy;
    private SerializerContext(SerializerStrategy serializerStrategy){
        this.serializerStrategy = serializerStrategy;
    }
    public static SerializerContext build(SerializerStrategy serializerStrategy){
        return new SerializerContext(serializerStrategy);
    }

    public byte getSerializerAlgorithm(){
        return serializerStrategy.getSerializerAlgorithm();
    }

    public byte[] serialize(Object object){
        return serializerStrategy.serialize(object);
    }
    public <T> T deserialize(Class<T> clazz, byte[] bytes){
        return serializerStrategy.deserialize(clazz, bytes);
    }
}
