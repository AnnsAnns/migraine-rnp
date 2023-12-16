package packets;

public class MessagePacket {
    char[] message; // 320 * u8

    public MessagePacket(char[] message) {
        this.message = message;
    }

    public char[] getMessage() {
        return message;
    }

    public void setMessage(char[] message) {
        this.message = message;
    }

    public byte[] toBytes() {
        byte[] bytes = new byte[320];
        for (int i = 0; i < 320; i++) {
            bytes[i] = (byte) this.message[i];
        }
        return bytes;
    }

    public static MessagePacket fromBytes(byte[] bytes) {
        char[] message = new char[320];
        for (int i = 0; i < 320; i++) {
            message[i] = (char) bytes[i];
        }
        return new MessagePacket(message);
    }
}
