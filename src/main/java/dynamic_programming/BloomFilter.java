package dynamic_programming;

/**
 * Create with: dynamic_programming.BloomFilter
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2020/7/29 14:19
 * version: 1.0
 * description: Bloom过滤器
 */

import com.google.common.base.Preconditions;

import java.util.Arrays;

public class BloomFilter {
    public static final double DEFAULT_FPP = 0.05D;
    protected BitSet bitSet;
    protected int numBits;
    protected int numHashFunctions;

    public BloomFilter() {
    }

    public BloomFilter(long expectedEntries) {
        this(expectedEntries, 0.05D);
    }

    public BloomFilter(long expectedEntries, double fpp) {
        Preconditions.checkArgument(expectedEntries > 0L, "expectedEntries should be > 0");
        Preconditions.checkArgument((fpp > 0.0D) && (fpp < 1.0D), "False positive probability should be > 0.0 & < 1.0");
        int nb = optimalNumOfBits(expectedEntries, fpp);

        this.numBits = (nb + (64 - nb % 64));
        this.numHashFunctions = optimalNumOfHashFunctions(expectedEntries, this.numBits);
        this.bitSet = new BitSet(this.numBits);
    }

    public BloomFilter(long[] bitSet, long expectedEntries, double fpp) {
        Preconditions.checkArgument(expectedEntries > 0L, "expectedEntries should be > 0");
        Preconditions.checkArgument((fpp > 0.0D) && (fpp < 1.0D), "False positive probability should be > 0.0 & < 1.0");
        int nb = optimalNumOfBits(expectedEntries, fpp);
        this.numBits = (nb + (64 - nb % 64));
        this.numHashFunctions = optimalNumOfHashFunctions(expectedEntries, this.numBits);
        this.bitSet = new BitSet(bitSet);
    }

    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round(m / n * Math.log(2.0D)));
    }

    static int optimalNumOfBits(long n, double p) {
        return (int) (-n * Math.log(p) / (Math.log(2.0D) * Math.log(2.0D)));
    }

    public void add(byte[] val) {
        if (val == null)
            addBytes(val, -1);
        else
            addBytes(val, val.length);
    }

    public void addBytes(byte[] val, int length) {
        long hash64 = val == null ? 2862933555777941757L : Murmur3.hash64(val, length);
        addHash(hash64);
    }

    private void addHash(long hash64) {
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);

        for (int i = 1; i <= this.numHashFunctions; i++) {
            int combinedHash = hash1 + i * hash2;

            if (combinedHash < 0) {
                combinedHash ^= -1;
            }
            int pos = combinedHash % this.numBits;
            this.bitSet.set(pos);
        }
    }

    public void addString(String val) {
        if (val == null)
            add(null);
        else
            add(val.getBytes());
    }

    public void addLong(long val) {
        addHash(getLongHash(val));
    }

    public void addDouble(double val) {
        addLong(Double.doubleToLongBits(val));
    }

    public boolean test(byte[] val) {
        if (val == null) {
            return testBytes(val, -1);
        }
        return testBytes(val, val.length);
    }

    public boolean testBytes(byte[] val, int length) {
        long hash64 = val == null ? 2862933555777941757L : Murmur3.hash64(val, length);
        return testHash(hash64);
    }

    private boolean testHash(long hash64) {
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);

        for (int i = 1; i <= this.numHashFunctions; i++) {
            int combinedHash = hash1 + i * hash2;

            if (combinedHash < 0) {
                combinedHash ^= -1;
            }
            int pos = combinedHash % this.numBits;
            if (!this.bitSet.get(pos)) {
                return false;
            }
        }
        return true;
    }

    public boolean testString(String val) {
        if (val == null) {
            return test(null);
        }
        return test(val.getBytes());
    }

    public boolean testLong(long val) {
        return testHash(getLongHash(val));
    }

    private long getLongHash(long key) {
        key = (key ^ 0xFFFFFFFF) + (key << 21);
        key ^= key >> 24;
        key = key + (key << 3) + (key << 8);
        key ^= key >> 14;
        key = key + (key << 2) + (key << 4);
        key ^= key >> 28;
        key += (key << 31);
        return key;
    }

    public boolean testDouble(double val) {
        return testLong(Double.doubleToLongBits(val));
    }

    public long sizeInBytes() {
        return getBitSize() / 8;
    }

    public int getBitSize() {
        return this.bitSet.getData().length * 64;
    }

    public int getNumHashFunctions() {
        return this.numHashFunctions;
    }

    public long[] getBitSet() {
        return this.bitSet.getData();
    }

    public String toString() {
        return "m: " + this.numBits + " k: " + this.numHashFunctions;
    }

    public void merge(BloomFilter that) {
        if ((this != that) && (this.numBits == that.numBits) && (this.numHashFunctions == that.numHashFunctions))
            this.bitSet.putAll(that.bitSet);
        else
            throw new IllegalArgumentException("BloomFilters are not compatible for merging. this - " + toString() + " that - " + that.toString());
    }

    public void reset() {
        this.bitSet.clear();
    }

    public class BitSet {
        private final long[] data;

        public BitSet(long bits) {
            this(new long[(int) Math.ceil(bits / 64.0D)]);
        }

        public BitSet(long[] data) {
            assert (data.length > 0) : "data length is zero!";
            this.data = data;
        }

        public void set(int index) {
            this.data[(index >>> 6)] |= 1L << index;
        }

        public boolean get(int index) {
            return (this.data[(index >>> 6)] & 1L << index) != 0L;
        }

        public long bitSize() {
            return this.data.length * 64L;
        }

        public long[] getData() {
            return this.data;
        }

        public void putAll(BitSet array) {
            assert (this.data.length == array.data.length) : ("BitArrays must be of equal length (" + this.data.length + "!= " + array.data.length + ")");
            for (int i = 0; i < this.data.length; i++)
                this.data[i] |= array.data[i];
        }

        public void clear() {
            Arrays.fill(this.data, 0L);
        }


    }

    public static void main(String args[]) {
        BloomFilter a = new BloomFilter(100, 0.01);
        System.out.print(a.getBitSize() + "\n");
        System.out.print(optimalNumOfBits(100, 0.01));
    }
}
