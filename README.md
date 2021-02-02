# Basic Car Insurance Estimator

This is a basic demo that show how to estimate car insurance.

## Stack

```
-Java
-Spring Boot
-JUnit 5
-TDD
```


## Endpoint

```bash
POST http://localhost:8080/api
```

## Request Body
Example 1:

```json
{
   brand: "Ford",
   year: 2016,
   coverageType: "full"
}
```

Example 2:

```json
{
   brand: "Audi",
   year: 2009,
   coverageType: "basic"
}
```

## Algorithm
Example with Ford 2019, Coverage Type Full

```
BASE = 200
Brand Rate => FORD_RATE= 1.15
YEAR_DECREASE_PERCENT = 3
COVERAGE_FULL_RATE = 1.15
CURRENT_YEAR = 2020

AGE_CAR = CURRENT_YEAR - 2019

SUM_ESTIMATED_PRICE = 200 * FORD_RATE

SUM_ESTIMATED_PRICE -= ((AGE_CAR * YEAR_DECREASE_PERCENT) * SUM_ESTIMATED_PRICE) / 100

SUM_ESTIMATED_PRICE = SUM_ESTIMATED_PRICE * COVERAGE_FULL_RATE

```




