Version 3.9.7 (19 Nov, 2021)
==================================
   * Improved multiprocess mode support

Version 3.9.6 (03 Nov, 2021)
==================================
   * Improved multiprocess mode support

Version 3.9.5 (27 Oct, 2021)
==================================
   * Add Localytics v6 Support

Version 3.9.4 (12 Oct, 2021)
==================================
   * Expose Apptimize.getMetadataSequenceNumber to help troubleshooting

Version 3.9.3 (29 Sep, 2021)
==================================
   * Stability enhancements for Android 11 emulators

Version 3.9.2 (22 Sep, 2021)
==================================
   * Automatically call MixPanel (if present) with userId changes for cohort tracking
   * Fixed multiprocess ANR

Version 3.9.0 (21 Sep, 2021)
==================================
   * Added ability to reset pilot targeting id by setting a null value

Version 3.9.0 (17 Aug, 2021)
==================================
    * Fix crash importing Mixpanel and Localytics events, introduced by AAR changes
    * Support for Mixpanel cohorts
      - Add Apptimize.setMixpanelIdentity()
      - Automatically import the Mixpanel.identify setting

Version 3.8.2 (03 Aug, 2021)
==================================
   * Disallow setting a UserID containing only whitespace

Version 3.8.1 (20 Jul, 2021)
==================================
    * Apptimize is available as AAR module with its own proguard rules. 
      Jar format is deprecated and will be disabled in future releases.
    * Fixed an issue when forceVariant and clearVariant might not work properly
    * Fixed an issue when Apptimize wasn't able to process metadata changes

Version 3.8.0 (13 Jul, 2021)
==================================
    * Added Apptimize.getApptimizeOptions(), which returns setup options, updated to
      reflect actual isDevicePairingEnabled, isVisualChangesEnabled,
      isThirdPartyEventExportingEnabled, getDeviceName, and getUpdateMetaDataTimeout
    * Optimize performance for SQLite database access

Version 3.7.26 (12 Jul, 2021)
==================================
    * Improve event reporting for multiprocess applications (ApptimizeOptions.setMultiprocessMode)
    * Improve internal sdk logging

Version 3.7.26 (25 Jun, 2021)
==================================
    * Optimize metadata management
    * Optimize multi-process communications (ApptimizeOptions.setMultiprocessMode)

Version 3.7.25 (11 Jun, 2021)
==================================
    * Stability enhancements. Fix a rare ANR race condition

Version 3.7.24 (09 Jun, 2021)
==================================
    * Stability enhancements

Version 3.7.23 (13 May, 2021)
==================================
    * Stability enhancements for multiprocess support (ApptimizeOptions.setMultiprocessMode)

Version 3.7.22 (11 May, 2021)
==================================
    * Fixed issue when some applications were not able to generate a pairing token to connect with dashboard

Version 3.7.21 (06 May, 2021)
==================================
    * Stability enhancements

Version 3.7.20 (20 Apr, 2021)
==================================
    * Improved multiprocess support (ApptimizeOptions.setMultiprocessMode)

Version 3.7.19 (17 Apr, 2021)
==================================
    * Remove support for Android 4.4 and 5.x
    * Improved metadata processing, and it always runs on a background thread
    * Stability enhancements

Version 3.7.18 (24 Feb, 2021)
==================================
    * Added isOffline and removeAllUserAttributes APIs

Version 3.7.17 (19 Feb, 2021)
==================================
    * Improved data consistency if user attributes were updated during metadata update
    * Optimize metadata processing

Version 3.7.16 (21 Jan, 2021)
==================================
    * Updated API documentation and Feature Variables support

Version 3.7.15 (18 Jan, 2021)
==================================
    * Stability enhancements
    * Support setting server region in Segment dashboard

Version 3.7.14 (08 Jan, 2021)
==================================
    * Fix some situations when OnApptimizeInitializedListener.onApptimizeInitialized
      does not get called after Apptimize.setup.

Version 3.7.13 (16 Dec, 2020)
==================================
    * Performance optimizations for Apptimize.setup and event uploads
    * Stability enhancements
    * Export experiment participation to Mixpanel
    * Add Apptimize.setLogMonitor to optionally pass all Apptimize log output to a custom logger

Version 3.7.10 (24 Nov, 2020)
==================================
    * Stability enhancements

Version 3.7.9 (09 Nov, 2020)
==================================
    * Fix all performance logging to use the ApptimizeLog settings
    * Improve logcat output when when the app needs to call Apptimize.setup first,
    * Support more server-side sdk configuration via metadata
    * Stability enhancements

Version 3.7.7 (20 Oct, 2020)
==================================
    * Stability enhancements. Fix an ANR when using the unit-testing-only Apptimize.reset() API.

Version 3.7.6 (19 Oct, 2020)
==================================
    * Stability enhancements
    * Change developer mode pairing to use a knock action and pairing token.
      * See: https://faq.apptimize.com/hc/en-us/articles/360018776634-How-can-I-pair-my-phone-with-the-Apptimize-dashboard
      * The clipboard check, already disabled for iOS 14+, has been completely removed.
      * Older SDKs will no longer be able to use the clipboard check to pair starting in January 2021.

Version 3.7.4 (14 Sep, 2020)
==================================
    * Stability enhancements
    * Add support for EU region customers

Version 3.7.1 (21 Jul, 2020)
==================================
    * Stability enhancements 

Version 3.7.0 (16 Jul, 2020)
==================================
    * Add metadata state monitoring functions. 
      See documentation for Apptimize.getMetadataState,
      Apptimize.addOnMetadataStateChangedListener and 
      Apptimize.removeOnMetadataStateChangedListener.

Version 3.6.11 (14 Jul, 2020)
==================================
    * Stability enhancements
    * Extend analytics opt-in mechanism to data export

Version 3.6.10 (05 Jun, 2020)
==================================
    * Fix images failing to download during application first loads on Visual Experiments and Instant Updates

Version 3.6.9 (29 May, 2020)
==================================
    * Support Firebase crashlytics  

Version 3.6.8 (27 May, 2020)
==================================
    * Update Airship integration for Airship v12.0~v13.x  

Version 3.6.7 (18 May, 2020)
==================================
    * Stability enhancements  

Version 3.6.6 (28 Apr, 2020)
==================================
    * Remove visual support for Android 4.4

Version 3.6.5 (15 Apr, 2020)
==================================
    * Fix possible flashing during removal of a customized visual element
    * Stability enhancements

Version 3.6.4 (09 Apr, 2020)
==================================
    * Improve performance of downloading metadata

Version 3.6.3 (01 Apr, 2020)
==================================
    * Expand functional support for Android 10

Version 3.6.2 (24 Mar, 2020)
==================================
    * Fix for rare ANRs on Android 5.0 through 7.1

Version 3.6.0 (28 Jan, 2020)
==================================
    * Support metadata-driven opt-in mechanism for integration with other analytics frameworks

Version 3.5.4 (27 Jan, 2020)
==================================
    * Fix visual experiments for some apks for Android-9 x86 and x86_64 simulators

Version 3.5.3 (20 Jan, 2020)
==================================
    * Work-around for an Android 8.x bug, where it can generate a 
      AssertionError for "No NameTypeIndex match for SHORT_DAYLIGHT"

Version 3.5.2 (10 Dec, 2019)
==================================
    * Suppresse extra calls to OnTestRunListener - number of calls to OnTestRunListener will now match the number posted in results.

Version 3.5.1 (07 Nov, 2019)
==================================
* Remove unnecessary logcat output

Version 3.5.0 (29 Oct, 2019)
==================================
* Add integration with Airship SDK to enable coordinated experiments and messaging campaigns
* Added Apptimize.getInstantUpdateOrWinnerInfo - to fetch instant update and winning experiment infod

Version 3.4.14 (18 Oct, 2019)
==================================
* Remove obfuscation of Android.OnApptimizeInitializedListener

Version 1.25.2 (6 Aug, 2019)
==================================
*Update to Apptimize 3.4.13

Version 1.25.1 (6 Aug, 2019)
==================================
*Update to Apptimize 3.4.9 and trigger latest version update

Version 1.0.24 (6 Aug, 2019)
==================================
*Update to Apptimize 3.4.9

Version 1.0.23 (11 June, 2019)
===================================
*Update to Apptimize 3.4.0

Version 1.0.22 (09 May, 2019)
===================================
*Update to Apptimize 3.3.5

Version 1.0.21 (30 January, 2019)
===================================
*Update to Apptimize 3.2.18

Version 1.0.20 (24 January, 2019)
===================================
*Update to Apptimize 3.2.17

Version 1.0.19 (13 December, 2018)
===================================
*Update to Apptimize 3.2.16

Version 1.0.18 (30 November, 2018)
===================================
*Update to Apptimize 3.2.14

Version 1.0.17 (11 September, 2018)
===================================
*Update to Apptimize 3.2.7

Version 1.0.16 (06 September, 2018)
===================================
*Update to Apptimize 3.2.6

Version 1.0.11 (01 March, 2018)
===================================
*Update to Apptimize 3.1.1

Version 1.0.10 (17 July, 2017)
===================================
*Update to Apptimize 2.13.0

Version 1.0.9 (07 July, 2017)
===================================
*Update to Apptimize 2.12.13

Version 1.0.8 (06 July, 2017)
===================================
*Only create participation events for the first time a user participates in an experiment.
*Update to Apptimize 2.12.12

Version 1.0.7 (14 June, 2017)
===================================
*Update to Apptimize 2.12.11

Version 1.0.6 (06 April, 2017)
===================================
*Update to Apptimize 2.12.9

Version 1.0.5 (05 April, 2017)
===================================
*Update to Apptimize 2.12.8

Version 1.0.4 (08 March, 2017))
===================================
*Update to Apptimize 2.12.5

Version 1.0.3 (17th February, 2017))
===================================
*Update to Apptimize 2.12.4

Version 1.0.2 (23rd January, 2017))
===================================
*Update to Apptimize 2.12.3

Version 1.0.1 (14th June, 2016)
===================================
*Change owner to Apptimize

Version 1.0.0 (26th April, 2016)
===================================
*(Supports analytics-android 4.0.+ and Apptimize 2.10.8+)*

  * Initial Release
