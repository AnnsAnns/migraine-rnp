package packets;

/*
 * Header
 */
public class HeaderPacket {
    // type - u8
    byte type;
    // ttl - u8
    byte ttl;
    // target - 3 * u8
    char[] target;
    // source - 3 * u8
    char[] source;
    // hops - u8
    byte hops;

    public HeaderPacket(byte type, byte ttl, char[] target, char[] source, byte hops) {
        this.type = type;
        this.ttl = ttl;
        this.target = target;
        this.source = source;
        this.hops = hops;
    }

    public byte getType() {
        return type;
    }

    public byte getTtl() {
        return ttl;
    }

    public char[] getTarget() {
        return target;
    }

    public char[] getSource() {
        return source;
    }

    public byte getHops() {
        return hops;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setTtl(byte ttl) {
        this.ttl = ttl;
    }

    public void setTarget(char[] target) {
        this.target = target;
    }

    public void setSource(char[] source) {
        this.source = source;
    }

    public void setHops(byte hops) {
        this.hops = hops;
    }

    public byte[] toBytes() {
        byte[] bytes = new byte[10];
        bytes[0] = type;
        bytes[1] = ttl;
        bytes[2] = (byte) target[0];
        bytes[3] = (byte) target[1];
        bytes[4] = (byte) target[2];
        bytes[5] = (byte) source[0];
        bytes[6] = (byte) source[1];
        bytes[7] = (byte) source[2];
        bytes[8] = hops;
        return bytes;
    }

    public static HeaderPacket fromBytes(byte[] bytes) {
        byte type = bytes[0];
        byte ttl = bytes[1];
        char[] target = new char[3];
        target[0] = (char) bytes[2];
        target[1] = (char) bytes[3];
        target[2] = (char) bytes[4];
        char[] source = new char[3];
        source[0] = (char) bytes[5];
        source[1] = (char) bytes[6];
        source[2] = (char) bytes[7];
        byte hops = bytes[8];
        return new HeaderPacket(type, ttl, target, source, hops);
    }
}
