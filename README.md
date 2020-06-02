# log-drain-endpoint

An https log drain endpoint used in testing log capture. This is intended to be a short lived project to measure log duplication and/or loss during the v13 cf-deployment upgrade. This **only** captures and stores logs emitted by the [Log Spammer](https://github.com/cloud-gov/log-spammer).

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

## Useful queries



### Missing Logs

```
select message_number + 1 as first_missing, (next_nc - 1) as last_missing, app_instance_guid
from (select log_message.*, lead(message_number) over (partition by app_instance_guid order by message_number) as next_nc
      from log_message
     ) log_message
where next_nc <> message_number + 1
```

### Duplicate Logs

```
SELECT
    app_instance_guid, message_number, COUNT(*)
FROM
    log_message
GROUP BY
    app_instance_guid, message_number
HAVING 
    COUNT(*) > 1;
```
