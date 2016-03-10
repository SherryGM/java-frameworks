# Dropwizard

http://www.dropwizard.io/

http://www.dropwizard.io/0.9.2/docs/getting-started.html

Started with Gradle build file in Eclipse

Took 30 minutes to get Hello World service running
 
## Worked with JDBI to get first Items service running

http://jdbi.org/
http://jdbi.org/getting_jdbi/
http://jdbi.org/sql_object_api_queries/
http://stackoverflow.com/questions/34436304/how-to-run-sql-script-file-using-jdbi

## API Work

It looks like the Dropwizard best practices are somewhat different than other frameworks. I opted
for similarity with other frameworks rather than a pure Dropwizard approach.

Struggled to figure out how to process POST form parameters:

http://stackoverflow.com/questions/6097166/how-do-i-read-post-parameters-for-a-restful-service-using-jersey

### Static Files

It is possible to configure static files outside the project, but this requires an additional
dependency. Also required some figuring out how to configure Dropwizard.

https://github.com/bazaarvoice/dropwizard-configurable-assets-bundle
http://stackoverflow.com/questions/18722936/how-to-use-bazaarvoice-dropwizard-configurable-assets-bundle
https://groups.google.com/forum/#!msg/dropwizard-user/a_jNCLE7oXM/J4B-R_FlYcEJ


6.5 hours on Dropwizard, JDBI
2 hour on Derby, Testing
Startup: 1.811 seconds