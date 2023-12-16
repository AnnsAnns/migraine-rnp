package packets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.CRC32;

public class Packet {
    private byte[] bytes;
    private long checksum;

    public Packet(byte[] bytes, long checksum) {
        this.bytes = bytes.clone();
        this.checksum = checksum;
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length + Long.BYTES);
        buffer.put(bytes);
        buffer.putLong(checksum);
        return buffer.array();
    }

    public static Packet fromBytes(byte[] bytes) {
        if (bytes.length == 0) {
            return new Packet(new byte[0], 0);
        }

        byte[] checksumBytes = new byte[Long.BYTES];
        System.arraycopy(bytes, bytes.length - Long.BYTES, checksumBytes, 0, Long.BYTES);

        ByteBuffer buffer = ByteBuffer.wrap(bytes, 0, bytes.length - Long.BYTES);
        byte[] messageBytes = new byte[buffer.remaining()];
        buffer.get(messageBytes);

        long checksum = ByteBuffer.wrap(checksumBytes).order(ByteOrder.BIG_ENDIAN).getLong();

        return new Packet(messageBytes, checksum);
    }

    public static Packet createCrc32(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);

        return new Packet(bytes, crc32.getValue());
    }

    public static boolean verifyCrc32(byte[] bytes, long checksum) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);

        return crc32.getValue() == checksum;
    }

    public boolean verifySelf() {
        return verifyCrc32(bytes, checksum);
    }
}
