package packets;

public class ConnectionPacket {
    char[] name; // 3 * u8
    char[] ip; // 4 * u8
    boolean isFirst; // u8

    public ConnectionPacket(char[] name, char[] ip, boolean isFirst) {
        this.name = name;
        this.ip = ip;
        this.isFirst = isFirst;
    }

    public char[] getName() {
        return name;
    }

    public char[] getIp() {
        return ip;
    }

    public boolean getIsFirst() {
        return isFirst;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    public void setIp(char[] ip) {
        this.ip = ip;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public String toString() {
        return "ConnectionPacket(name=" + java.util.Arrays.toString(this.name) + ", ip=" + java.util.Arrays.toString(this.ip) + ", isFirst=" + this.isFirst + ")";
    }

    public byte[] toBytes() {
        byte[] bytes = new byte[12];
        bytes[0] = (byte) this.name[0];
        bytes[1] = (byte) this.name[1];
        bytes[2] = (byte) this.name[2];
        bytes[3] = (byte) this.ip[0];
        bytes[4] = (byte) this.ip[1];
        bytes[5] = (byte) this.ip[2];
        bytes[6] = (byte) this.ip[3];
        bytes[7] = (byte) (this.isFirst ? 1 : 0);
        return bytes;
    }

    public static ConnectionPacket fromBytes(byte[] bytes) {
        char[] name = new char[3];
        name[0] = (char) bytes[0];
        name[1] = (char) bytes[1];
        name[2] = (char) bytes[2];
        char[] ip = new char[4];
        ip[0] = (char) bytes[3];
        ip[1] = (char) bytes[4];
        ip[2] = (char) bytes[5];
        ip[3] = (char) bytes[6];
        boolean isFirst = bytes[7] == 1;
        return new ConnectionPacket(name, ip, isFirst);
    }
}
