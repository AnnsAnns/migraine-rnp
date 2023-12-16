package packets;

public class RoutingEntry {
    char[] info_source; // 3 * u8
    char[] destination; // 3 * u8
    char[] ip; // 4 * u8
    byte port; // u16
    byte hops; // u8

    public RoutingEntry(char[] info_source, char[] destination, char[] ip, byte port, byte hops) {
        this.info_source = info_source;
        this.destination = destination;
        this.ip = ip;
        this.port = port;
        this.hops = hops;
    }

    public char[] getInfoSource() {
        return info_source;
    }

    public char[] getDestination() {
        return destination;
    }

    public char[] getIp() {
        return ip;
    }

    public byte getPort() {
        return port;
    }

    public byte getHops() {
        return hops;
    }

    public void setInfoSource(char[] info_source) {
        this.info_source = info_source;
    }

    public void setDestination(char[] destination) {
        this.destination = destination;
    }

    public void setIp(char[] ip) {
        this.ip = ip;
    }

    public void setPort(byte port) {
        this.port = port;
    }

    public void setHops(byte hops) {
        this.hops = hops;
    }

    /**
     * Converts the RoutingEntry to a string for debugging purposes
     * 
     * @return string
     */
    public String toString() {
        return "RoutingEntry(info_source=" + java.util.Arrays.toString(this.info_source) + ", destination="
                + java.util.Arrays.toString(this.destination) + ", ip=" + java.util.Arrays.toString(this.ip) + ", port="
                + this.port + ", hops=" + this.hops + ")";
    }

    /**
     * Converts the RoutingEntry to a byte array
     * 
     * @return byte array
     */
    public byte[] toBytes() {
        byte[] bytes = new byte[15];
        bytes[0] = (byte) this.info_source[0];
        bytes[1] = (byte) this.info_source[1];
        bytes[2] = (byte) this.info_source[2];
        bytes[3] = (byte) this.destination[0];
        bytes[4] = (byte) this.destination[1];
        bytes[5] = (byte) this.destination[2];
        bytes[6] = (byte) this.ip[0];
        bytes[7] = (byte) this.ip[1];
        bytes[8] = (byte) this.ip[2];
        bytes[9] = (byte) this.ip[3];
        bytes[10] = (byte) this.port;
        bytes[11] = (byte) this.hops;
        return bytes;
    }

    /**
     * Converts a byte array to a RoutingEntry
     * 
     * @param bytes byte array
     * @return RoutingEntry
     */
    public static RoutingEntry fromBytes(byte[] bytes) {
        char[] info_source = new char[3];
        info_source[0] = (char) bytes[0];
        info_source[1] = (char) bytes[1];
        info_source[2] = (char) bytes[2];
        char[] destination = new char[3];
        destination[0] = (char) bytes[3];
        destination[1] = (char) bytes[4];
        destination[2] = (char) bytes[5];
        char[] ip = new char[4];
        ip[0] = (char) bytes[6];
        ip[1] = (char) bytes[7];
        ip[2] = (char) bytes[8];
        ip[3] = (char) bytes[9];
        byte port = bytes[10];
        byte hops = bytes[11];
        return new RoutingEntry(info_source, destination, ip, port, hops);
    }
}
