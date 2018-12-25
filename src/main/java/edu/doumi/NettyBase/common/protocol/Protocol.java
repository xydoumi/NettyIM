package edu.doumi.NettyBase.common.protocol;

/**
 *  transfer protocol
 */
public interface Protocol {

    /**
     *  to sure protocol is correct.is a number occupy 4 byte
     */
    int MAGIC_NUMBER = 0x19920301;

    /**
     *  protocol version occupy 1 byte
     */
    byte version = 1;

    /**
     * a constant to DATE_LENGTH offset
     */
    int DATA_OFFSET = 7;

    /**
     * a constant to DATE_LENGTH occupy position
     */
    int DATE_LENGTH = 4;

}
