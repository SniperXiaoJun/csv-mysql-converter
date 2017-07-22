## Simple CSV mysql converter 

[![Build Status](https://travis-ci.org/timlien/csv-mysql-converter.svg?branch=master)](https://travis-ci.org/timlien/csv-mysql-converter)

A simple command line program to convert `predefined csv file` to *MySQL query*.      


##### Predefined CSV file format

Suppose your csv file has following format.   

| ``id`` | ``username`` | ``create_date``         | ``TRUNCATE,TABLE `users`; INSERT INTO `users` (`id`, `username`, `create_date`) VALUES"`` |
|:---|:---------|:--------------------|:---------------------------------------------------------------------------------------|
| 1  | alex     | 2017-01-01 00:00:00 | "(1,'alex','2017-01-01 00:00:00'),"                                                    |
| 2  | andy     | 2017-01-01 00:00:00 | "(2,'andy','2017-01-01 00:00:00'),"                                                    |
| 3  | tim      | 2017-01-01 00:00:00 | "(3,'tim','2017-01-01 00:00:00'),"                                                     |
| 4  | tom      | 2017-01-01 00:00:00 | "(4,'tom','2017-01-01 00:00:00'),"                                                     |
| 5  | zoe      | 2017-01-01 00:00:00 | "(5,'zoe','2017-01-01 00:00:00'),"                                                     |


#### MySQL query

After conversion, csv file will be converted into following MySQL query.

```sql
TRUNCATE  TABLE `users`;
INSERT INTO `users` (`id`, `username`, `create_date`) VALUES
(1,'alex','2017-01-01 00:00:00'),
(2,'andy','2017-01-01 00:00:00'),
(3,'tim','2017-01-01 00:00:00'),
(4,'tom','2017-01-01 00:00:00'),
(5,'zoe','2017-01-01 00:00:00');
```

#### Build

```shell
$ gradlew build
```

#### Command

Once built, the shell command can help you convert csv file into MySQL query.

```shell
$ convert.sh /data/users.csv
```
