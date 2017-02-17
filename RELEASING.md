Releasing
========

 1. Change the version in `gradle.properties` to a non-SNAPSHOT version:
    presumably, the current version name is X.Y.Z-SNAPSHOT. Just take the 
    "-SNAPSHOT" off the name, so it is "VERSION_NAME=X.Y.Z"
 2. Update the `CHANGELOG.md` for the impending release.
 3. Update the Android version number in build.gradle, in the line:
    compile 'com.apptimize:apptimize-android:2.12.4'
 4. `git commit -am "Prepare for release X.Y.Z."` (where X.Y.Z is the new version)
 5. `git tag -a X.Y.Z -m "Version X.Y.Z"` (where X.Y.Z is the new version)
 6. `./gradlew clean uploadArchives`
 7. Update the `gradle.properties` to the next SNAPSHOT version. E.g., use the
    next version number, with "VERSION_NAME=X.Y.Z-SNAPSHOT"
 8. `git commit -am "Prepare next development version."`
 9. `git push && git push --tags`
 10. Visit [Sonatype Nexus](https://oss.sonatype.org/) and promote the artifact.
