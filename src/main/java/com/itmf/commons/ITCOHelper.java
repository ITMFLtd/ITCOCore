package com.itmf.commons;

public final class ITCOHelper {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private ITCOHelper() {} // Class should never be instantiated

    /**
     * Converts an array of bytes into their String representation. Note that there are no "0x"
     * prefixes, just the value of the byte is included in the string. For example, if you passed a
     * byte array containing two bytes, [0xCA, 0xFE], the returned string would be "CAFE". Bytes in
     * the string will always be uppercase.
     *
     * @param bytes An array of bytes to be represented as a string.
     * @return A string representation of the passed byte array.
     */
    public static String toHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Returns a string containing an array of bytes as a byte array. This inverses the {@link
     * #toHex(byte[])} method - if a string containing "CAFEBABE" is passed to this method, it will
     * return a byte array containing: 0xCA, 0xFE, 0xBA, 0xBE.
     *
     * @param s A string containing hex bytes.
     * @return A byte array containing the bytes of a passed string.
     */
    public static byte[] toByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }


}
