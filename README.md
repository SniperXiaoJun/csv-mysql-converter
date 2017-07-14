## Simple CSV mysql converter 

A simple command program to convert `predefined csv file` to *sql query*.      


##### Predefined CSV file format

Suppose your csv contains file format as below.   

| ``id`` | ``username`` | ``create_date``         | ``TRUNCATE,TABLE `users`; INSERT INTO `users` (`id`, `username`, `create_date`) VALUES"`` |
|:---|:---------|:--------------------|:---------------------------------------------------------------------------------------|
| 1  | alex     | 2017-01-01 00:00:00 | "(1,'alex','2017-01-01 00:00:00'),"                                                    |
| 2  | andy     | 2017-01-01 00:00:00 | "(2,'andy','2017-01-01 00:00:00'),"                                                    |
| 3  | tim      | 2017-01-01 00:00:00 | "(3,'tim','2017-01-01 00:00:00'),"                                                     |
| 4  | tom      | 2017-01-01 00:00:00 | "(4,'tom','2017-01-01 00:00:00'),"                                                     |
| 5  | zoe      | 2017-01-01 00:00:00 | "(5,'zoe','2017-01-01 00:00:00'),"                                                     |


#### Sql query

After conversion, csv file will be convert to follow sql query.

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

Once build, your terminal shell can convert predefined csv file to sql query.

```shell
$ convert.sh /data/users.csv
```
