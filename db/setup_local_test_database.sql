DROP DATABASE IF EXISTS latte_test;
DROP USER IF EXISTS latte_test;
CREATE DATABASE latte_test;
CREATE USER latte_test IDENTIFIED BY 'latte_test';
GRANT ALL PRIVILEGES ON latte_test.* TO 'latte_test';
