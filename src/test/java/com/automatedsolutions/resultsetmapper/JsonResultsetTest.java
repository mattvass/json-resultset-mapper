package com.automatedsolutions.resultsetmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.json.JsonObject;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.automatedsolutions.resultsetmapper.common.BaseTestHelper;
import com.automatedsolutions.resultsetmapper.constants.JsonBuilderConstants;
import static com.automatedsolutions.resultsetmapper.common.TestResultSetData.*;
import static com.automatedsolutions.resultsetmapper.common.TestColumnNames.*;

import static org.mockito.Mockito.*;

public class JsonResultsetTest extends BaseTestHelper {

  @Mock private ResultSet results;

  @Mock private ResultSetMetaData meta;

  @InjectMocks JsonResultSet jsonResultSet;

  @Test
  public void toJsonTest() throws SQLException {

    when(results.getMetaData()).thenReturn(meta);
    when(meta.getColumnCount()).thenReturn(10);

    doReturn(true).doReturn(true).doReturn(false).when(results).next();

    when(meta.getColumnName(1)).thenReturn(STRING_COLUMN);
    when(meta.getColumnClassName(1)).thenReturn(STRING_VALUE.getClass().getTypeName());
    when(results.getString(STRING_COLUMN)).thenReturn(STRING_VALUE);

    when(meta.getColumnName(2)).thenReturn(INTEGER_COLUMN);
    when(meta.getColumnClassName(2)).thenReturn(INTEGER_VALUE.getClass().getTypeName());
    when(results.getObject(INTEGER_COLUMN)).thenReturn(INTEGER_VALUE);

    when(meta.getColumnName(3)).thenReturn(BOOLEAN_COLUMN);
    when(meta.getColumnClassName(3)).thenReturn(BOOLEAN_VALUE.getClass().getTypeName());
    when(results.getObject(BOOLEAN_COLUMN)).thenReturn(BOOLEAN_VALUE);

    when(meta.getColumnName(4)).thenReturn(DOUBLE_COLUMN);
    when(meta.getColumnClassName(4)).thenReturn(DOUBLE_VALUE.getClass().getTypeName());
    when(results.getObject(DOUBLE_COLUMN)).thenReturn(DOUBLE_VALUE);

    when(meta.getColumnName(5)).thenReturn(BIGDECIMAL_COLUMN);
    when(meta.getColumnClassName(5)).thenReturn(BIGDECIMAL_VALUE.getClass().getTypeName());
    when(results.getBigDecimal(BIGDECIMAL_COLUMN)).thenReturn(BIGDECIMAL_VALUE);

    when(meta.getColumnName(6)).thenReturn(SHORT_COLUMN);
    when(meta.getColumnClassName(6)).thenReturn(SHORT_VALUE.getClass().getTypeName());
    when(results.getObject(SHORT_COLUMN)).thenReturn(SHORT_VALUE);

    when(meta.getColumnName(7)).thenReturn(LONG_COLUMN);
    when(meta.getColumnClassName(7)).thenReturn(LONG_VALUE.getClass().getTypeName());
    when(results.getObject(LONG_COLUMN)).thenReturn(LONG_VALUE);

    when(meta.getColumnName(8)).thenReturn(TIME_COLUMN);
    when(meta.getColumnClassName(8)).thenReturn(TIME_VALUE.getClass().getTypeName());
    when(results.getObject(TIME_COLUMN)).thenReturn(TIME_VALUE);

    when(meta.getColumnName(9)).thenReturn(TIMESTAMP_COLUMN);
    when(meta.getColumnClassName(9)).thenReturn(TIMESTAMP_VALUE.getClass().getTypeName());
    when(results.getObject(TIMESTAMP_COLUMN)).thenReturn(TIMESTAMP_VALUE);

    when(meta.getColumnName(10)).thenReturn(DATE_COLUMN);
    when(meta.getColumnClassName(10)).thenReturn(DATE_VALUE.getClass().getTypeName());
    when(results.getObject(DATE_COLUMN)).thenReturn(DATE_VALUE);

    JsonObject json = jsonResultSet.toJson(results);

    Assert.assertNotNull(json);
    Assert.assertEquals(2, json.getJsonArray(JsonBuilderConstants.JSON_RESULTS_KEY).size());
  }

  @Test
  public void toJsonResultSetNullTest() throws SQLException {
    ResultSet resultSet = null;

    JsonObject json = jsonResultSet.toJson(resultSet);

    Assert.assertNotNull(json);
    Assert.assertEquals(
        JsonBuilderConstants.RESULT_SET_NULL,
        json.getString(JsonBuilderConstants.JSON_RESULTS_KEY));
  }

  @Test
  public void toJsonNormalizeColumnNamesTest() throws SQLException {

    when(results.getMetaData()).thenReturn(meta);
    when(meta.getColumnCount()).thenReturn(2);

    doReturn(true).doReturn(false).when(results).next();

    when(meta.getColumnName(1)).thenReturn(STRING_UNDERSCORE_COLUMN);
    when(meta.getColumnClassName(1)).thenReturn(STRING_VALUE.getClass().getTypeName());
    when(results.getString(STRING_UNDERSCORE_COLUMN)).thenReturn(STRING_VALUE);

    when(meta.getColumnName(2)).thenReturn(INTEGER_HYPHEN_COLUMN);
    when(meta.getColumnClassName(2)).thenReturn(INTEGER_VALUE.getClass().getTypeName());
    when(results.getObject(INTEGER_HYPHEN_COLUMN)).thenReturn(INTEGER_VALUE);

    JsonObject json = jsonResultSet.toJson(results, true);

    Assert.assertNotNull(json);
    Assert.assertFalse(
        json.getJsonArray(JsonBuilderConstants.JSON_RESULTS_KEY)
            .getJsonObject(0)
            .containsKey(STRING_UNDERSCORE_COLUMN));
    Assert.assertFalse(
        json.getJsonArray(JsonBuilderConstants.JSON_RESULTS_KEY)
            .getJsonObject(0)
            .containsKey(INTEGER_HYPHEN_COLUMN));
    Assert.assertTrue(
        json.getJsonArray(JsonBuilderConstants.JSON_RESULTS_KEY)
            .getJsonObject(0)
            .containsKey(STRING_COLUMN));
    Assert.assertTrue(
        json.getJsonArray(JsonBuilderConstants.JSON_RESULTS_KEY)
            .getJsonObject(0)
            .containsKey(INTEGER_COLUMN));
  }
}
