Releasing
========

 1. Change the version in `gradle.properties` and remove -SNAPSHOT from version:
    presumably, the current version name is 1.25.X-SNAPSHOT. Just take the 
    "-SNAPSHOT" off the name, so it is "VERSION_NAME=1.0.X"
 2. Update the `CHANGELOG.md` for the impending release.
 3. Update the Android version number in build.gradle, in the line:
    compile 'com.apptimize:apptimize-android:2.12.4'
 4. `git commit -am "Prepare for release 1.25.X."` (where 1.0.X is the new version)
 5. `git tag -a 1.0.X -m "Version 1.25.X"` (where 1.25.X is the new version)
 6. `./gradlew clean uploadArchives`
 7a. Update the `gradle.properties` to the next version code,
     e.g. Update the VERSION_CODE=10X to 10Y, where Y==X+1. These must be increasing.
     After 1099 you will have to check the staging repository to see what version
     code they assign following the upload, then fix everthing to match.
 7b. Update the `gradle.properties` to the next SNAPSHOT version. E.g., use the
     next version number, with "VERSION_NAME=1.0.Y-SNAPSHOT" to match the VERSION_CODE
 8. `git commit -am "Prepare next development version."`
 9. `git push && git push --tags`
 10. Visit [Sonatype Nexus](https://oss.sonatype.org/) and promote the artifact.
