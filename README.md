# README

## How to run

1. Before you run this, please start mariaDB, and init local database firstly

   1. Connect to MariaDB

      `mariadb -u root -p`

      ```
      $ mariadb -u root -p
      Enter password: 
      Welcome to the MariaDB monitor.  Commands end with ; or \g.
      Your MariaDB connection id is 14
      Server version: 10.5.9-MariaDB Homebrew
      
      Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.
      
      Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
      ```

   2. Init the Database

      `source /Your/local/path/to/init.sql`

      

   3. Init the Data

      `source /Your/path/to/data.sql`

      

   4. Download the jar file in tag v1.0 Run in terminal by jar file

      `java -jar /your/local/path/to/app-all.jar`



## 