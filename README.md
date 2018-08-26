# Json ResultSet Mapper

This allows users to simply convert their result set to json. 

The following types will be cast to the appropriate type:
- String
- Integer
- BigDecimal
- Long
- Double
- Boolean
- Short

Currently all other values are converted to strings values or null.

For any result set that returns primitive types, getObject is used instead and it's properly cast to it's non-primitive type.

**Simple Implementation**
```
new JsonResultSet().toJson(resultSet);
```
#### Example Json Output
```
{
	"results": [{
		"stringColumn": "string value",
		"integerColumn": 39,
		"booleanColumn": true,
		"doubleColumn": 150000.0,
		"bigdecimalColumn": 10,
		"shortColumn": 123,
		"longColumn": 12345678910,
		"timeColumn": "10:11:12",
		"timestampColumn": "1970-05-23 17:21:18.91",
		"dateColumn": "1970-05-23"
	}, {
		"stringColumn": "string value",
		"integerColumn": 39,
		"booleanColumn": true,
		"doubleColumn": 150000.0,
		"bigdecimalColumn": 10,
		"shortColumn": 123,
		"longColumn": 12345678910,
		"timeColumn": "10:11:12",
		"timestampColumn": "1970-05-23 17:21:18.91",
		"dateColumn": "1970-05-23"
	}]
}
```
