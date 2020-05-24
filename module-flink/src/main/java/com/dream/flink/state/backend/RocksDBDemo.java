package com.dream.flink.state.backend;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

/**
 * @author fanrui03
 * @time 2020-05-24 14:49:45
 */
public class RocksDBDemo {

    private static byte[] KEY = "key".getBytes();
    private static String ROCKSDB_DIR = "/Users/fanrui03/Documents/tmp/rocksdb";

    public static void main(String[] args) throws RocksDBException {

        RocksDB db = RocksDB.open(ROCKSDB_DIR);
        db.compactRange();
        db.put(KEY, "value".getBytes());
        System.out.println(new String(db.get(KEY)));

    }

}
