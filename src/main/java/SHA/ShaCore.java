package SHA;

public class ShaCore {

    long[][] w80;
    public long[] k = new long[]{0x428A2F98D728AE22L, 0x7137449123EF65CDL, 0xB5C0FBCFEC4D3B2FL, 0xE9B5DBA58189DBBCL, 0x3956C25BF348B538L,
            0x59F111F1B605D019L, 0x923F82A4AF194F9BL, 0xAB1C5ED5DA6D8118L, 0xD807AA98A3030242L, 0x12835B0145706FBEL,
            0x243185BE4EE4B28CL, 0x550C7DC3D5FFB4E2L, 0x72BE5D74F27B896FL, 0x80DEB1FE3B1696B1L, 0x9BDC06A725C71235L,
            0xC19BF174CF692694L, 0xE49B69C19EF14AD2L, 0xEFBE4786384F25E3L, 0x0FC19DC68B8CD5B5L, 0x240CA1CC77AC9C65L,
            0x2DE92C6F592B0275L, 0x4A7484AA6EA6E483L, 0x5CB0A9DCBD41FBD4L, 0x76F988DA831153B5L, 0x983E5152EE66DFABL,
            0xA831C66D2DB43210L, 0xB00327C898FB213FL, 0xBF597FC7BEEF0EE4L, 0xC6E00BF33DA88FC2L, 0xD5A79147930AA725L,
            0x06CA6351E003826FL, 0x142929670A0E6E70L, 0x27B70A8546D22FFCL, 0x2E1B21385C26C926L, 0x4D2C6DFC5AC42AEDL,
            0x53380D139D95B3DFL, 0x650A73548BAF63DEL, 0x766A0ABB3C77B2A8L, 0x81C2C92E47EDAEE6L, 0x92722C851482353BL,
            0xA2BFE8A14CF10364L, 0xA81A664BBC423001L, 0xC24B8B70D0F89791L, 0xC76C51A30654BE30L, 0xD192E819D6EF5218L,
            0xD69906245565A910L, 0xF40E35855771202AL, 0x106AA07032BBD1B8L, 0x19A4C116B8D2D0C8L, 0x1E376C085141AB53L,
            0x2748774CDF8EEB99L, 0x34B0BCB5E19B48A8L, 0x391C0CB3C5C95A63L, 0x4ED8AA4AE3418ACBL, 0x5B9CCA4F7763E373L,
            0x682E6FF3D6B2B8A3L, 0x748F82EE5DEFB2FCL, 0x78A5636F43172F60L, 0x84C87814A1F0AB72L, 0x8CC702081A6439ECL,
            0x90BEFFFA23631E28L, 0xA4506CEBDE82BDE9L, 0xBEF9A3F7B2C67915L, 0xC67178F2E372532BL, 0xCA273ECEEA26619CL,
            0xD186B8C721C0C207L, 0xEADA7DD6CDE0EB1EL, 0xF57D4F7FEE6ED178L, 0x06F067AA72176FBAL, 0x0A637DC5A2C898A6L,
            0x113F9804BEF90DAEL, 0x1B710B35131C471BL, 0x28DB77F523047D84L, 0x32CAAB7B40C72493L, 0x3C9EBE0A15C9BEBCL,
            0x431D67C49C100D4CL, 0x4CC5D4BECB3E42B6L, 0x597F299CFC657E2AL, 0x5FCB6FAB3AD6FAECL, 0x6C44198C4A475817L};

    private long circuloShift(long x, int y) {
        return (x >>> y) | (x << (64 - y));
    }

    public long sig0(long w) {

        return circuloShift(w,1) ^ circuloShift(w,8) ^ (w >>> 7);
    }
    public long sig1(long w) {

        return circuloShift(w,19) ^ circuloShift(w,61) ^ (w >>> 6);
    }

    public void set80words(long[][] blocks) {

        w80 = new long[blocks.length][80];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < 16; j++) w80[i][j] = blocks[i][j];
            for (int j = 16; j < 80; j++){
                w80[i][j] = sig1(w80[i][j-2]) + w80[i][j-7] + sig0(w80[i][j-15]) + w80[i][j-16];
            }
        }
    }

    public long Ch(long e, long f, long g) {

        return (e & f) ^ (~e & g);
    }
    public long BigSig1(long e) {

        return circuloShift(e,14) ^ circuloShift(e,18) ^ circuloShift(e,41);
    }

    public long BigSig0(long a) {

        return circuloShift(a,28) ^ circuloShift(a,34) ^ circuloShift(a,39);
    }

    public long Maj(long a, long b, long c) {

        return (a & b) ^ (b & c) ^ (a & c);
    }

    public String shaf(int blockNum) {
        long[] WV = new long[]{
                0x6A09E667F3BCC908L,
                0xBB67AE8584CAA73BL,
                0x3C6EF372FE94F82BL,
                0xA54FF53A5F1D36F1L,
                0x510E527FADE682D1L,
                0x9B05688C2B3E6C1FL,
                0x1F83D9ABFB41BD6BL,
                0x5BE0CD19137E2179L
        };

        long a,b,c,d,e,f,g,h,t1,t2;

        for (int i = 0; i < blockNum; i++) {
            a = WV[0];
            b = WV[1];
            c = WV[2];
            d = WV[3];
            e = WV[4];
            f = WV[5];
            g = WV[6];
            h = WV[7];

            for (int j = 0; j < 80; j++) {
                t1 = h + Ch(e,f,g) + BigSig1(e) + k[j] + w80[i][j];
                t2 = Maj(a,b,c) + BigSig0(a);
                h = g;
                g = f;
                f = e;
                e = d + t1;
                d = c;
                c = b;
                b = a;
                a = t1 + t2;
            }
            WV[0] = a + WV[0];
            WV[1] = b + WV[1];
            WV[2] = c + WV[2];
            WV[3] = d + WV[3];
            WV[4] = e + WV[4];
            WV[5] = f + WV[5];
            WV[6] = g + WV[6];
            WV[7] = h + WV[7];
        }
        StringBuilder msgDigest = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            msgDigest.append(Long.toHexString(WV[i]));
        }
        return msgDigest.toString();
    }

}
