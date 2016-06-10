package com.apptimize.segment;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.apptimize.Apptimize;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
import com.segment.analytics.Traits;
import com.segment.analytics.core.tests.BuildConfig;
import com.segment.analytics.integrations.Logger;
import com.segment.analytics.test.AliasPayloadBuilder;
import com.segment.analytics.test.GroupPayloadBuilder;
import com.segment.analytics.test.IdentifyPayloadBuilder;
import com.segment.analytics.test.ScreenPayloadBuilder;
import com.segment.analytics.test.TrackPayloadBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static com.segment.analytics.Analytics.LogLevel.VERBOSE;
import static com.segment.analytics.Utils.createTraits;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, manifest = Config.NONE)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest(Apptimize.class) //
public class ApptimizeTest {
  @Rule public PowerMockRule rule = new PowerMockRule();
  @Mock Application context;
  @Mock Analytics analytics;
  ApptimizeIntegration integration;

  @Before public void setUp() {
    initMocks(this);
    PowerMockito.mockStatic(Apptimize.class);
    integration = new ApptimizeIntegration(analytics, "foo", true, Logger.with(VERBOSE));
    PowerMockito.mockStatic(Apptimize.class); // Reset mock.
  }

  @Test public void initialize() {
    when(analytics.getApplication()).thenReturn(context);

    integration = new ApptimizeIntegration(analytics, "foo", false, Logger.with(VERBOSE));

    assertThat(integration.analytics).isNotNull().isEqualTo(analytics);
    verifyStatic();
    Apptimize.setup(context, "foo");
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void initializeWithRoots() {
    when(analytics.getApplication()).thenReturn(context);

    integration = new ApptimizeIntegration(analytics, "foo", true, Logger.with(VERBOSE));

    assertThat(integration.analytics).isNotNull().isEqualTo(analytics);
    verifyStatic();
    Apptimize.setup(context, "foo");
    verifyStatic();
    Apptimize.setOnExperimentRunListener(integration);
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void activityCreate() {
    Activity activity = mock(Activity.class);
    Bundle bundle = mock(Bundle.class);
    integration.onActivityCreated(activity, bundle);
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void activityStart() {
    Activity activity = mock(Activity.class);
    integration.onActivityStarted(activity);
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void activityResume() {
    Activity activity = mock(Activity.class);
    integration.onActivityResumed(activity);
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void activityPause() {
    Activity activity = mock(Activity.class);
    integration.onActivityPaused(activity);
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void activityStop() {
    Activity activity = mock(Activity.class);
    integration.onActivityStopped(activity);
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void activitySaveInstance() {
    Activity activity = mock(Activity.class);
    Bundle bundle = mock(Bundle.class);
    integration.onActivitySaveInstanceState(activity, bundle);
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void activityDestroy() {
    Activity activity = mock(Activity.class);
    integration.onActivityDestroyed(activity);
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void track() {
    integration.track(new TrackPayloadBuilder().event("pressPlay").build());
    verifyStatic();
    Apptimize.track(eq("pressPlay"));
  }

  @Test public void trackNumeric() {
    integration.track(new TrackPayloadBuilder().event("pressPlay")
        .properties(new Properties().putValue("value", 3.1))
        .build());
    verifyStatic();
    Apptimize.track(eq("pressPlay"), eq(3.1));
  }

  @Test public void trackNumericInteger() {
    integration.track(new TrackPayloadBuilder().event("pressPlay")
        .properties(new Properties().putValue("value", 3))
        .build());
    verifyStatic();
    Apptimize.track(eq("pressPlay"), eq(3.0));
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void alias() {
    integration.alias(new AliasPayloadBuilder().build());
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void identify() {
    Traits traits = createTraits().putValue("anonymousId", "foo").putValue("userId", "bar");
    integration.identify(new IdentifyPayloadBuilder().traits(traits).build());
    verifyStatic();
    Apptimize.setUserAttribute("anonymousId", "foo");
    verifyStatic();
    Apptimize.setUserAttribute("userId", "bar");
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void identifyTraits() {
    Traits traits = createTraits().putValue("anonymousId", "bar")
        .putValue("userId", "foo")
        .putAge(78)
        .putCreatedAt("March 15, 1935");
    integration.identify(new IdentifyPayloadBuilder().traits(traits).build());

    verifyStatic();
    Apptimize.setUserAttribute("anonymousId", "bar");
    verifyStatic();
    Apptimize.setUserAttribute("userId", "foo");
    verifyStatic();
    Apptimize.setUserAttribute("age", 78);
    verifyStatic();
    Apptimize.setUserAttribute("createdAt", "March 15, 1935");
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void group() {
    integration.group(new GroupPayloadBuilder().build());
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void screen() {
    integration.screen(new ScreenPayloadBuilder().name("payments").build());
    verifyStatic();
    Apptimize.track(eq("Viewed payments Screen"));
  }

  @Test public void flush() {
    integration.flush();
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void reset() {
    integration.reset();
    verifyNoMoreInteractions(Apptimize.class);
  }

  @Test public void roots() {
    integration.onExperimentRun("foo", "bar", false);

    verify(analytics).track("Experiment Viewed", new Properties() //
        .putValue("experimentName", "foo").putValue("variationName", "bar"));
  }
}
