# OpenDaylightHackProject
Skeleton OpenDaylight Hydrogen Project

###Pre-requisite:

You should already have OpenDaylight Hydrogen source code downloaded & working with eclipse.

###Setup:

1. Pull the code of a blank/dummy hack application from my git.<LINK> 

1. Copy this project under "controller/opendaylight/" where rest of the opendaylight modules are present.

1. Import this project into eclipse as a Maven project.

1. Start putting in your code & you are good to go.

###Testing:

Once you build the above project, it will install a jar in target folder of the project.

There are two ways of testing.

1. Copy this jar manually into plugins folder of distribution.

1. Make a entry into the parent & opendaylight root pom for this new module & rebuild.
