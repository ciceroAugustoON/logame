package logame.db;

import java.util.Map;

import java.util.LinkedHashMap;

public class JdbcClient {

    private JdbcClient() {}
    
    public static JdbcClient create() {
        return new JdbcClient();
    }

    public StatementSpec sql(String sql) {
        return new StatementSpec(sql);
    }

    public class StatementSpec {
        private String sql;
        private Map<Integer, String> params;

        private StatementSpec(String sql) {
            this.sql = sql;
            params = new LinkedHashMap<>();
        }

        public StatementSpec param(Integer jdbcIndex, Object value) {
            params.put(jdbcIndex, convertToSQL(value));
            return this;
        }

        public String query() { // Just for test
            try {
                return assembleQuery();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private Integer findPlaceHolder(Integer index, String sql) {
            for (int  i = 0; i < sql.length(); i++) {
                if (sql.charAt(i) == ':') {
                    return i;
                }
            }
            return -1;
        }

        private String convertToSQL(Object value) {
            if (value instanceof String) {
                return "'" + value + "'";
            }
            return value.toString();
        }

        private String assembleQuery() throws Exception{
            String query = sql;

            for (Map.Entry<Integer, String> entry : params.entrySet()) {
                if (entry.getKey() == -1) {
                    throw new Exception("Invalid Position to entry: " + entry.getValue());
                }

                int start = findPlaceHolder(entry.getKey(), query);
                int end = endIndex(start, query);

                if (end == -1) {
                    query = query.replace(query.substring(start), entry.getValue());
                } else {
                    query = query.replace(query.substring(start, end), entry.getValue());
                }
            }

            return query;
        }

        private int endIndex(Integer key, String query) {
            int end = query.indexOf(',', key);

            if (end == -1) {
                end = query.indexOf(' ', key);
            }
            if (end == -1) {
                end = query.indexOf(')', key);
            }
            
            return end;
        }
    }
}


