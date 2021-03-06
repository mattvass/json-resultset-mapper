# Json ResultSet Mapper

[![Maven Central](https://img.shields.io/maven-central/v/io.github.mattvass/json-resultset-mapper.svg)](http://repo1.maven.org/maven2/io/github/mattvass/json-resultset-mapper/)
[![CircleCI](https://img.shields.io/circleci/project/github/mattvass/json-resultset-mapper.svg)](https://circleci.com/gh/mattvass/json-resultset-mapper)


This allows users to simply convert their result set to json. 

The following types will be cast to the appropriate type:
- String
- Integer
- BigDecimal
- Long
- Double
- Boolean
- Short

Currently all other values are converted to strings values or JsonValue.NULL if the returned value is null.

The **java.sql.ResultSet.getObject** method is used to ensure no primitive types are set, due to methods like **getBoolean** or **getDouble** returning primitive values which have defaults. The **getObject** method seemed like the logical choice to ensure we are not setting any default values that weren't expected by the end user.

#### Add as a dependency
```
<dependency>
  <groupId>io.github.mattvass</groupId>
  <artifactId>json-resultset-mapper</artifactId>
  <version>see-version-number-above</version>
</dependency>
```

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
