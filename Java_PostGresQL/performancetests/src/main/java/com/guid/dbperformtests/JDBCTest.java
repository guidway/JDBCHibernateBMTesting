package com.guid.dbperformtests;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import com.guid.dbperformtests.models.HBMillion;

public class JDBCTest {
    private final String url = "jdbc:postgresql://localhost/performancedb_tests";
    private final String user = "<some username>";
    private final String password = "<some password>";
    Connection conn = null;
    private static final String TABLE_NAME = "jdbcMillion";
    private static final int numOfFields = 20;
    private static final int numOfRecords = 1000000;

    private Connection connect() {

        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }

    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean tableExists() {
        connect();
        boolean tableExists = false;
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            // check if "employee" table is there
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME, null);
            if (tables.next()) {
                tableExists = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tableExists;
    }

    public void oneMillionSingleRecordInsertTest() {

        try {
            connect();
            Random rand = new Random();
            for (int r = 0; r < numOfRecords; r++) {
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT INTO ");
                sb.append(TABLE_NAME);
                sb.append(
                        "(some_value1, some_value2, some_value3, some_value4, some_value5, some_value6, some_value7, some_value8, some_value9, some_value10, some_value11, some_value12, some_value13, some_value14, some_value15, some_value16, some_value17, some_value18, some_value19, some_value20, some_value21) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                PreparedStatement st = conn.prepareStatement(sb.toString());
                for (int i = 1; i <= numOfFields; i++) {
                    if (i == 5)
                        st.setLong(i, rand.nextLong());
                    else if (i == 10)
                        st.setDouble(i, rand.nextDouble());
                    else
                        st.setString(i, "new_value");
                }
                st.executeUpdate();
                st.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void oneMillionBatchRecordInsertTest() {
        try {
            connect();
            conn.setAutoCommit(false);

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
            sb.append(TABLE_NAME);
            sb.append(
                    "(some_value1, some_value2, some_value3, some_value4, some_value5, some_value6, some_value7, some_value8, some_value9, some_value10, some_value11, some_value12, some_value13, some_value14, some_value15, some_value16, some_value17, some_value18, some_value19, some_value20, some_value21) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement st = conn.prepareStatement(sb.toString());
            Random rand = new Random();

            for (int r = 0; r < numOfRecords; r++) {
                for (int i = 1; i <= numOfFields; i++) {
                    if (i == 5)
                        st.setLong(i, rand.nextLong());
                    else if (i == 10)
                        st.setDouble(i, rand.nextDouble());
                    else
                        st.setString(i, "new_value");
                }
                Double[] objArray = new Double[625];
                for (int j = 0; j < 625; j++) {
                    objArray[j] = rand.nextDouble();
                }
                Array testArray = conn.createArrayOf("FLOAT", objArray);
                st.setArray(numOfFields + 1, testArray);
                st.addBatch();
                if (r % 100 == 0) {
                    st.executeBatch();
                    conn.commit();
                }
            }
            st.executeBatch();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        connect();
        String createQueryString = generateCreateQueryString();
        try (Connection conn = this.connect()) {
            Statement createTable = conn.createStatement();
            createTable.execute(createQueryString);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int fetchAllData() {
        connect();
        int i = 0;

        // List<HBMillion> millionList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(TABLE_NAME);
        try {
            PreparedStatement st = conn.prepareStatement(sb.toString());
            ResultSet rst = st.executeQuery();
            while (rst.next()) {
                i++;
                HBMillion million = new HBMillion();

                million.setSome_value1(rst.getString(2));
                million.setSome_value2(rst.getString(3));
                million.setSome_value3(rst.getString(4));
                million.setSome_value4(rst.getString(5));
                million.setSome_value5(rst.getLong(6));
                million.setSome_value6(rst.getString(7));
                million.setSome_value7(rst.getString(8));
                million.setSome_value8(rst.getString(9));
                million.setSome_value9(rst.getString(10));
                million.setSome_value10(rst.getDouble(11));
                million.setSome_value11(rst.getString(12));
                million.setSome_value12(rst.getString(13));
                million.setSome_value13(rst.getString(14));
                million.setSome_value14(rst.getString(15));
                million.setSome_value15(rst.getString(16));
                million.setSome_value16(rst.getString(17));
                million.setSome_value17(rst.getString(18));
                million.setSome_value18(rst.getString(19));
                million.setSome_value19(rst.getString(20));
                million.setSome_value20(rst.getString(21));
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return i;
    }

    private String generateCreateQueryString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(TABLE_NAME);
        sb.append("(id SERIAL ");
        for (int i = 1; i <= numOfFields; i++) {
            sb.append(",some_value");
            sb.append(i);
            if (i == 5)
                sb.append(" BIGINT");
            else if (i == 10)
                sb.append(" DOUBLE PRECISION");
            else
                sb.append(" VARCHAR(255)");
        }
        sb.append(", some_value21 DOUBLE PRECISION[]");
        sb.append(")");
        return sb.toString();
    }
}