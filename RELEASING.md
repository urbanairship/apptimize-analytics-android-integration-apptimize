Releasing
========

1. Change gradle.properties for VERSION_CODE and VERSION_NAME:

		 VERSION_CODE=muupp
		 VERSION_NAME=m.u.p

		 where "m" is the major version
						"u" is the minor version ("uu" is 2 digits with a leading zero when u is 0..9)
						"p" is the patch level ("pp" is a 2 digits with a leading zero when p is 0..9)

2. Update CHANGELOG.md for the impending release. This has the format:

		 Version m.u.p (dd Mmm, yyyy)
		 ==================================
		 ...the change-log.txt entry from android lib/change-log.txt,
		 ...with any internal lines removed.
	 
		 For example,
	 
		 Version 3.4.14 (18 Oct, 2019)
		 ==================================
		 * Remove obfuscation of Android.OnApptimizeInitializedListener

3. Update build.gradle with the new build number:

		 compile 'com.apptimize:apptimize-android:m.u.p@aar'

4. git commit -am "Prepare for release m.u.p."

5. git tag -a m.u.p -m "Version m.u.p"

6. ./gradlew clean uploadArchives

7. Make sure you have a github token to authenticate the push. You use a token
   instead of your password. You can get a token by logging into github
   and using the Settings > Developer Settings > Personal Access Tokens menu.

   git push && git push --tags

8. Visit [Sonatype Nexus](https://oss.sonatype.org/) and promote the artifact.
