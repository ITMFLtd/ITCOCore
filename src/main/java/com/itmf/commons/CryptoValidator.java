package com.itmf.commons;

public final class CryptoValidator {

        private CryptoValidator() {}

        private static void checkArgument(boolean expression, String msg) {
            if (!expression) {
                throw new IllegalArgumentException(msg);
            }
        }

        public static void checkHash(byte[] hash) {
            checkArgument(hash.length == 32, "Invalid hash length");
        }

        public static void checkSignature(byte[] signature) {
            checkArgument(signature.length == 64, "Invalid signature length");
        }

        public static void checkKey(byte[] key) {
            checkArgument(key.length == 32, "Invalid key length");
        }


        public static void checkSeed(byte[] seed) {
            checkArgument(seed.length == 32, "Invalid seed length");
        }

}
