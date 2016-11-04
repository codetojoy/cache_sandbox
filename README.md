
### cache_sandbox

* simple illustration of Google Guava's `CacheBuilder`
* the `AccountDao` queries an in-memory database with a pathogenic sleep to simulate heavy load
* the `Runner` queries 1000 records via a cache
    * as expected, the first run takes 30 seconds and the second run is much faster
