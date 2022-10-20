# Drone manager API
Small spring boot app for managing drones


## Requirements

For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle 7.4](https://gradle.org/)

## The backend is done with

- Spring Boot
- Spring
- Hibenate
- Java Core

## How to run it?

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the olorini.Main.java class from your IDE.

Alternatively you can use go to the project folder and run:

```shell
gradlew bootRun
```

After the server is running, go to http://localhost:8080/

## API

### <a name="drone_link"/>To see a list of all drones use GET http://localhost:8080/drones/fleet

  - Input parameters: No
  - Output parameters: JSON

| Name | Description                                                               |
| ------ |---------------------------------------------------------------------------|
| id | Unique identifier od drone                                                |
| serial number | Serial number (100 characters max)                                        |
| model | drone's model (Lightweight, Middleweight, Cruiserweight, Heavyweight)     |
| weightLimit| Weight limit (500gr max)                                                  |
| batteryCapacity| Battey capacity (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING) |

```json
[
    {
        "id": 1,
        "serialNumber": "088002C",
        "model": "CRUISERWEIGHT",
        "weightLimit": 300,
        "batteryCapacity": 65
    },
    {
        "id": 2,
        "serialNumber": "088001C",
        "model": "CRUISERWEIGHT",
        "weightLimit": 320,
        "batteryCapacity": 85
    }
]
```

### <a name="med_link"/>To see a list of medication use GET http://localhost:8080/drones/medication

  - Input parameters: No
  - Output parameters: JSON

| Name | Description                                                              |
| -- |---------------------------------------------------------------------------|
| id | Unique identifier of drone                                                |
| name | name                                                 |
| weight | weight     |
| code | code                                                  |
| image| image - array of bytes (excluded it, very long)|

```json
[
  {
    "id": 1,
    "name": "aspirin",
    "weight": 10,
    "code": "ME001"
  },
  {
    "id": 33,
    "name": "crestor",
    "weight": 60,
    "code": "ME002"
  }
]
```

### To register a new drone use POST http://localhost:8080/drones/register

  - Input parameters: JSON
  
| Name | Description                                                               |
| ------ |---------------------------------------------------------------------------|
| serial number | Serial number (100 characters max)                                        |
| model | drone's model (Lightweight, Middleweight, Cruiserweight, Heavyweight)     |
| weightLimit| Weight limit (500gr max)                                                  |
| batteryCapacity| Battey capacity (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING) |

```json
{
  "serialNumber": "088004L",
  "model": "MIDDLEWEIGHT",
  "weightLimit": 200,
  "batteryCapacity": 85
}
```
  - Output parameters: JSON

| Name | Description                                                             |
| -- |---------------------------------------------------------------------------|
| id | Unique identifier of drone|

```json
{
  "id": 162
}
```

### To load a drone with medicine POST http://localhost:8080/drones/load

- Input parameters: JSON

| Name | Description                  |
| ------ |------------------------------|
| droneId | Drone's identifier           |
| medicineIds | List of medicine identifiers |

```json
{
  "droneId":22,
  "medicineIds": [33]
}
```
- Output parameters: Status 200 OK or Error info

### To check loaded medication items for a given drone GET http://localhost:8080/drones/get_medication?droneId=?

- Input parameters: 

| Name | Description                  |
| ------ |------------------------------|
| droneId | Drone's identifier           |

- Output parameters: JSON. See output parameters of /medication service [Link](med_link)

### To check available drones for loading GET http://localhost:8080/drones/get_idle_drones

- Input parameters: NO
- Output parameters: JSON. See output parameters of /fleet service [Link](drone_link)

### To check drone battery level for a given drone GET http://localhost:8080/drones/get_battery_level?droneId=?

- Input parameters:

| Name | Description                  |
| ------ |------------------------------|
| droneId | Drone's identifier           |

- Output parameters: JSON

| Name | Description                  |
| ------ |------------------------------|
| batteryLevel | Battery level                |

```json
{
  "batteryLevel": 100
}
```
### To get a journal of drones' battery levels GET http://localhost:8080/drones/journal

- Input parameters: NO
- Output parameters: text/html;charset=UTF-8

```text
2022-10-20 00:35:12 088002C 65 NORMAL
2022-10-20 00:35:12 088001C 85 NORMAL
2022-10-20 00:35:12 088003C 90 NORMAL
2022-10-20 00:35:12 088001L 90 NORMAL
2022-10-20 00:35:12 088002L 92 NORMAL
2022-10-20 00:35:12 088001H 100 NORMAL
2022-10-20 00:35:12 088002H 100 NORMAL
```