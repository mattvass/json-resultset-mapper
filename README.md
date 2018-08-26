# Json ResultSet Mapper

This allows users to simply convert their result set to json. 

The following types will be cast to the appropriate type:
- String
- Integer
- BigDecimal
- Long
- Double
- Boolean
- Short ( converted to int value if not null otherwise null is returned )

For any result set that returns primitive types, getObject is used instead and it's properly cast to it's non-primitive type.

Currently all other values are converted to strings values or null.

**Simple Implementation**
```
new JsonResultSet().toJson(resultSet);
```
