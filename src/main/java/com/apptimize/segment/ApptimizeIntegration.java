package com.apptimize.segment;

import com.apptimize.Apptimize;
import com.apptimize.Apptimize.OnExperimentRunListener;
import com.apptimize.ApptimizeOptions;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
import com.segment.analytics.ValueMap;
import com.segment.analytics.integrations.IdentifyPayload;
import com.segment.analytics.integrations.Integration;
import com.segment.analytics.integrations.Logger;
import com.segment.analytics.integrations.ScreenPayload;
import com.segment.analytics.integrations.TrackPayload;
import java.util.Map.Entry;

/**
 * Apptimize allows you to instantly update your native app without waiting for
 * App or Play Store approvals, and easily see if the change improved the app
 * with robust A/B testing analytics.
 *
 * @see <a href="http://www.apptimize.com/">Apptimize</a>
 * @see <a href="https://segment.com/docs/integrations/apptimize/">Apptimize Integration</a>
 */
public class ApptimizeIntegration extends Integration<Void> implements OnExperimentRunListener {
  public static final Factory FACTORY = new Factory() {
    @Override public Integration<?> create(ValueMap settings, Analytics analytics) {
      String appkey = settings.getString("appkey");
      boolean listen = settings.getBoolean("listen", false);
      boolean eucs = settings.getBoolean("apptimizeEuDataCenter", false);
      return new ApptimizeIntegration(analytics, appkey, listen, eucs, analytics.logger(APPTIMIZE_KEY));
    }

    @Override public String key() {
      return APPTIMIZE_KEY;
    }
  };

  private static final String APPTIMIZE_KEY = "Apptimize";
  private static final String VIEWED_EVENT_FORMAT = "Viewed %s Screen";

  final Analytics analytics;
  final Logger logger;

  ApptimizeIntegration(Analytics analytics, String appKey, boolean listen, boolean eucs, Logger logger)
      throws IllegalStateException {
    this.analytics = analytics;
    this.logger = logger;
    ApptimizeOptions apptimizeOptions = new ApptimizeOptions();
    if(eucs) {
      apptimizeOptions.setServerRegion(ApptimizeOptions.ServerRegion.EUCS);
    }
    Apptimize.setup(analytics.getApplication(), appKey, apptimizeOptions);
    this.logger.verbose("Apptimize.setup(context, %s)", appKey);
    if (listen) {
      Apptimize.setOnExperimentRunListener(this);
      this.logger.verbose("Apptimize.setOnExperimentRunListener()");
    }
  }

  @Override public void identify(IdentifyPayload identify) {
    super.identify(identify);
    for (Entry<String, Object> entry : identify.traits().entrySet()) {
      String key = entry.getKey();
      if (entry.getValue() instanceof Integer) {
        int value = (Integer) entry.getValue();
        Apptimize.setUserAttribute(key, value);
        logger.verbose("Apptimize.setUserAttribute(%s, %s)", key, value);
      } else {
        String value = String.valueOf(entry.getValue());
        Apptimize.setUserAttribute(key, value);
        logger.verbose("Apptimize.setUserAttribute(%s, %s)", key, value);
      }
    }
  }

  @Override public void track(TrackPayload track) {
    super.track(track);
    String event = track.event();
    double value = track.properties().getDouble("value", Double.MIN_VALUE);
    if (value == Double.MIN_VALUE) {
      Apptimize.track(event);
      logger.verbose("Apptimize.track(%s)", event);
    } else {
      Apptimize.track(event, value);
      logger.verbose("Apptimize.track(%s, %s)", event, value);
    }
  }

  @Override public void screen(ScreenPayload screen) {
    super.screen(screen);
    String event = String.format(VIEWED_EVENT_FORMAT, screen.event());
    Apptimize.track(event);
    logger.verbose("Apptimize.track(%s)", event);
  }

  @Override
  public void onExperimentRun(String experimentName, String variantName, boolean firstRun) {
    if (firstRun) {
      analytics.track(
              "Experiment Viewed",
              new Properties().putValue("experimentName", experimentName)
          .putValue("variationName", variantName));
    }
  }
}
