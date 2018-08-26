package com.automatedsolutions.resultsetmapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.apache.commons.text.WordUtils;
import com.automatedsolutions.resultsetmapper.constants.JsonBuilderConstants;

/** @author Matthew Vass */
public class JsonResultSet {

  private boolean isNormalized;

  /**
   * Converts the java.sql.ResultSet to a JsonObject using the column names as its keys. Values
   * returned can be null if no value is in the ResultSet for that column.
   *
   * <pre>
   * <code>
   * {
   * "results": [{
   * "string_column": "string value",
   * "integer_column": 39
   * }]
   * }
   * </code>
   * </pre>
   *
   * @param resultSet
   * @return JsonObject
   * @throws SQLException
   */
  public JsonObject toJson(ResultSet resultSet) throws SQLException {
    return toJson(resultSet, false);
  }

  /**
   * * Converts the java.sql.ResultSet to a JsonObject using normalized column names as its keys.
   * Values returned can be null if no value is in the ResultSet for that column.
   *
   * <pre>
   * <code>
   * {
   * "results": [{
   * "stringColumn": "string value",
   * "integerColumn": 39
   * }]
   * }
   * </code>
   * </pre>
   *
   * @param resultSet
   * @param normalizeColumnNames
   * @return JsonObject
   * @throws SQLException
   */
  public JsonObject toJson(ResultSet resultSet, boolean normalizeColumnNames) throws SQLException {

    this.isNormalized = normalizeColumnNames;

    if (resultSet == null) {
      return Json.createObjectBuilder()
          .add(JsonBuilderConstants.JSON_RESULTS_KEY, JsonBuilderConstants.RESULT_SET_NULL)
          .build();
    }

    JsonObjectBuilder jsonParent = Json.createObjectBuilder();
    JsonArrayBuilder jsonArray = Json.createArrayBuilder();

    ResultSetMetaData meta = resultSet.getMetaData();
    int colCount = meta.getColumnCount();

    while (resultSet.next()) {
      JsonObjectBuilder jsonResults = Json.createObjectBuilder();
      for (int i = 1; i <= colCount; i++) {
        String column = meta.getColumnName(i);
        String type = meta.getColumnClassName(i);

        if (type.equals(String.class.getTypeName())) {
          jsonResults.add(getColumnName(column), resultSet.getString(column));
        } else if (type.equals(Integer.class.getTypeName())) {
          jsonResults.add(getColumnName(column), (Integer) resultSet.getObject(column));
        } else if (type.equals(BigDecimal.class.getTypeName())) {
          jsonResults.add(getColumnName(column), resultSet.getBigDecimal(column));
        } else if (type.equals(Long.class.getTypeName())) {
          jsonResults.add(getColumnName(column), (Long) resultSet.getObject(column));
        } else if (type.equals(Double.class.getTypeName())) {
          jsonResults.add(getColumnName(column), (Double) resultSet.getObject(column));
        } else if (type.equals(Boolean.class.getTypeName())) {
          jsonResults.add(getColumnName(column), (Boolean) resultSet.getObject(column));
        } else {
          Object value = resultSet.getObject(column);
          jsonResults.add(
              getColumnName(column),
              (value != null ? String.valueOf(resultSet.getObject(column)) : "null"));
        }
      }
      jsonArray.add(jsonResults.build());
    }

    return jsonParent.add(JsonBuilderConstants.JSON_RESULTS_KEY, jsonArray.build()).build();
  }
  /**
   * This will return the column name or a normalized version of the column name.
   *
   * @param column
   * @return column name
   */
  private String getColumnName(String column) {
    if (isNormalized) {
      column = column.replaceAll("[^A-Za-z0-9]", " ");
      column = WordUtils.capitalizeFully(column).replace(" ", "");
      column = column.substring(0, 1).toLowerCase() + column.substring(1, column.length());
    }

    return column;
  }
}
