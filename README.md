# log-drain-endpoint

An https log drain endpoint used in testing log capture. This is intended to be a short lived project to measure log duplication and/or loss during the v13 cf-deployment upgrade. 

See:
- https://github.com/cloud-gov/cg-deploy-cf/issues/491
- https://github.com/cloud-gov/cg-deploy-cf/issues/487



## Test

```
./gradlew test
```

## Build

```
./gradlew clean assemble
```

## Deploy to CF

Create a postgres instance named `log-drain-endpoint-db` (if you using the supplied manifest). Push using the supplied manifest.

```
cf push
```

