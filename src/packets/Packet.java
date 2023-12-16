package packets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.CRC32;

public class Packet {
    private byte[] bytes; // The contents of the Packet
    private long checksum; // CRC32 checksum

    /**
     * Constructor for Packet class.
     *
     * @param bytes    the byte array to be stored in the Packet
     * @param checksum the checksum of the byte array
     */
    public Packet(byte[] bytes, long checksum) {
        this.bytes = bytes.clone();
        this.checksum = checksum;
    }

    /**
     * Converts the Packet object to a byte array.
     *
     * @return a byte array representation of the Packet object
     */
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length + Long.BYTES);
        buffer.put(bytes);
        buffer.putLong(checksum);
        return buffer.array();
    }

    /**
     * Creates a Packet object from a byte array.
     *
     * @param bytes the byte array to convert into a Packet object
     * @return a Packet object created from the byte array
     */
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

    /**
     * Creates a Packet object with a CRC32 checksum.
     *
     * @param bytes the byte array to convert into a Packet object with a CRC32 checksum
     * @return a Packet object created from the byte array with a CRC32 checksum
     */
    public static Packet createCrc32(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);

        return new Packet(bytes, crc32.getValue());
    }

    /**
     * Verifies the checksum of a byte array.
     *
     * @param bytes    the byte array to verify
     * @param checksum the checksum to verify against
     * @return true if the checksum is valid, false otherwise
     */
    public static boolean verifyCrc32(byte[] bytes, long checksum) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);

        return crc32.getValue() == checksum;
    }

    /**
     * Verifies the checksum of the Packet object.
     *
     * @return true if the checksum is valid, false otherwise
     */
    public boolean verifySelf() {
        return verifyCrc32(bytes, checksum);
    }
}
