# Json ResultSet Mapper

This allows users to simply convert their result set to json. 

The following types will be cast to the appropriate non-primitive type:
- String
- Integer
- BigDecimal
- Long
- Double
- Boolean

Currently all other values are converted to strings.

**Simple Implementation**
```
new JsonResultSet().toJson(resultSet);
```
