package logame.db.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import logame.db.ConvertException;
import logame.db.DBTemplate.Column;
import logame.db.DBTemplate.ForeignKey;
import logame.db.DBTemplate.NotNull;
import logame.db.DBTemplate.PrimaryKey;
import logame.db.DBTemplate.Table;
import logame.db.DBTemplate.Varchar;
import logame.entities.enumerations.LogState;

public class JavaToSQLMapper {

    private static final Map<Class<?>, String> javaToSqlTypeMap = new HashMap<>();

    static {
        javaToSqlTypeMap.put(String.class, "VARCHAR");
        javaToSqlTypeMap.put(Integer.class, "INT");
        javaToSqlTypeMap.put(int.class, "INT");
        javaToSqlTypeMap.put(Long.class, "BIGINT");
        javaToSqlTypeMap.put(long.class, "BIGINT");
        javaToSqlTypeMap.put(Float.class, "FLOAT");
        javaToSqlTypeMap.put(float.class, "FLOAT");
        javaToSqlTypeMap.put(Double.class, "DOUBLE");
        javaToSqlTypeMap.put(double.class, "DOUBLE");
        javaToSqlTypeMap.put(Boolean.class, "BOOLEAN");
        javaToSqlTypeMap.put(boolean.class, "BOOLEAN");
        javaToSqlTypeMap.put(java.util.Date.class, "DATE");
        javaToSqlTypeMap.put(java.sql.Date.class, "DATE");
        javaToSqlTypeMap.put(java.sql.Timestamp.class, "TIMESTAMP");

        // Unique Type
        javaToSqlTypeMap.put(LogState.class, "VARCHAR");
    }

    public static String getSqlType(Field field) {
        if (field == null) {
            throw new IllegalArgumentException("Attribute cannot be null");
        }

        Class<?> fieldType = field.getType();
        String sqlType = javaToSqlTypeMap.get(fieldType);

        if (sqlType == null) {
            throw new UnsupportedOperationException("Unsupported Java type: " + fieldType.getName());
        }

        return sqlType;
    }

    public static <T> String entityToTable(Class<T> entity) {
        StringBuilder table = new StringBuilder();
        String endLine = "";

        if (entity.getAnnotation(Table.class) != null) {
            table.append(entity.getAnnotation(Table.class).tableName());
        } else {
            throw new ConvertException("Entity does not have Table annotation");
        }

        table.append("(\n");

        Field[] fields = entity.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            
            if (field.getAnnotation(Column.class) != null) {
                table.append(field.getAnnotation(Column.class).columnName());
            } else {
                table.append(field.getName());
            }
            table.append(" ");

            table.append(getSqlType(field));
            if (field.getAnnotation(Varchar.class) != null) {
                table.append("(" + field.getAnnotation(Varchar.class).limit() + ")");
            }

            if (field.getAnnotation(PrimaryKey.class) != null) {
                table.append(" PRIMARY KEY");
            } else if (field.getAnnotation(ForeignKey.class) != null) {
                ForeignKey fk = field.getAnnotation(ForeignKey.class);
                if (!endLine.isEmpty()) {
                    endLine += ",\n";
                }
                endLine += "FOREIGN KEY(" + field.getName() + ") REFERENCES " + fk.tableName() + "(" + fk.foreignFieldName() + ")";
            }

            if (field.getAnnotation(NotNull.class) != null) {
                table.append(" NOT NULL");
            }

            if (i + 1 < fields.length) {
                table.append(",\n");
            }
        }

        if (!endLine.isBlank()) {
            table.append(",\n");
            table.append(endLine);
        }

        table.append("\n);");

        return table.toString();
    }
}
