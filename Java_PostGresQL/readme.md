# Benchmark Tests fo JDBC and PostGres!

A few benchmark tests comparing JDBC and Hibernate with a PostGres database using 21 mixed type fields with and without arrays.

***
1 ID (BIGINT) Field, 20 String Fields - no arrays
(Times recorded are in seconds)

Test Runs - Creating 1 million records
1) JDBC - 41, Hibernate - 130
2) JDBC - 40, Hibernate - 133
3) JDBC - 41, Hibernate - 131

Test Runs - Reading 1 million records
1) JDBC - 7, Hibernate - 12
2) JDBC - 10, Hibernate - 12
3) JDBC - 8, Hibernate - 13

***

2 BIGINT, 1 double, 18 string fields - no arrays
(Times recorded are in seconds)

Test Runs - Creating 1 million records
1) JDBC - 80, Hibernate - 130
2) JDBC - 73, Hibernate - 155
3) JDBC - 72, Hibernate - 129
4) JDBC - 75, Hibernate - 129

Test Runs - Reading 1 million records
1) JDBC - 7.6, Hibernate - 13.3
2) JDBC - 6.2, Hibernate - 11.6
3) JDBC - 6.7, Hibernate - 10

***
2 BIGINT 1 double, 18 strings and 1 double array
(Times recorded are in seconds)
Some Mixed field types were 2 BIGINT, 1 double, 17 strings, and 1 double precision array)

Test Runs - Creating 1 million records
1) JDBC - 192, Hibernate - NA
2) JDBC - 192.3, Hibernate - NA

Test Runs - Reading 1 million records
1) JDBC - 137, Hibernate - NA
2) JDBC - 125, Hibernate - NA
***
PSQL Sorting Benchmarks
Using the same fields above (2 BIGINT, 1 double, 18 srings and 1 double array), I tested sorting on the table and determined the following results:

1) Sorting on double field with 1 million records selecting all fields (including array) 
   1) Test Run 1: 92 seconds
   2) Test Run 2: 93 seconds
2) Sorting on double field with 1 million records selecting all BUT not array field 
   1) Test Run 1: 2.1 seconds
   2) Test Run 2: 2 seconds
