package edu.doumi.nettyBase.common.serializer;

import com.alibaba.fastjson.JSON;

public class JsonSerializer implements SerializerStrategy {
    private byte serializerAlgorithm = 1;
    @Override
    public byte getSerializerAlgorithm() {
        return serializerAlgorithm;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
