package ca.hobin.disablefrpdemo;

import java.util.ArrayList;

import android.app.admin.DevicePolicyManager;
import android.app.admin.FactoryResetProtectionPolicy;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button pushConfigButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pushConfigButton = findViewById(R.id.push_config_button);
        pushConfigButton.setOnClickListener((view) -> disableFrp());
    }

    private void disableFrp() {
        Context context = getApplicationContext();
        ComponentName admin = new ComponentName(context, DeviceAdmin.class);
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);

        Log.i("FRP-Demo", "Disabling FRP");
        FactoryResetProtectionPolicy policy = new FactoryResetProtectionPolicy.Builder()
                .setFactoryResetProtectionAccounts(new ArrayList<>())
                .setFactoryResetProtectionEnabled(false)
                .build();
        dpm.setFactoryResetProtectionPolicy(admin, policy);
        Log.i("FRP-Demo", "Done disabling FRP");
    }

}
