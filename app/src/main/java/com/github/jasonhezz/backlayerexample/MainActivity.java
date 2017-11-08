package com.github.jasonhezz.backlayerexample;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.design.backlayer.BackLayerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends AppCompatActivity {

  BackLayerLayout backLayer;
  ImageView primaryExpandIcon;
  ImageView secondaryExpandIcon;
  ImageView primaryExtraContent;
  ImageView secondaryExtraContent;

  private static final int PRIMARY = 0;
  private static final int SECONDARY = 1;

  @Retention(RetentionPolicy.SOURCE) @IntDef(value = { PRIMARY, SECONDARY })
  private @interface BackLayerExperience {
  }

  @BackLayerExperience private int experience = PRIMARY;

  private class SwitchExperienceOnClickListener implements View.OnClickListener {
    @BackLayerExperience private final int buttonExperience;

    public SwitchExperienceOnClickListener(@BackLayerExperience int buttonExperience) {
      this.buttonExperience = buttonExperience;
    }

    @Override public void onClick(View view) {
      if (experience == buttonExperience) {
        backLayer.setExpanded(!backLayer.isExpanded());
      } else {
        experience = buttonExperience;
        if (experience == PRIMARY) {
          primaryExtraContent.setVisibility(View.VISIBLE);
          secondaryExtraContent.setVisibility(View.GONE);
        } else {
          primaryExtraContent.setVisibility(View.GONE);
          secondaryExtraContent.setVisibility(View.VISIBLE);
        }
        backLayer.setExpanded(true);
      }
    }
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test);
    backLayer = findViewById(R.id.design_backlayer_backlayer_layout);
    backLayer.setExpanded(true);
    primaryExtraContent = findViewById(R.id.design_backlayer_extra_content);
    secondaryExtraContent = findViewById(R.id.design_backlayer_secondary_extra_content);
    primaryExpandIcon = findViewById(R.id.design_backlayer_primary_expand_icon);
    primaryExpandIcon.setOnClickListener(new SwitchExperienceOnClickListener(PRIMARY));
    secondaryExpandIcon = findViewById(R.id.design_backlayer_secondary_expand_icon);
    secondaryExpandIcon.setOnClickListener(new SwitchExperienceOnClickListener(SECONDARY));
  }
}
