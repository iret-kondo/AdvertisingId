package jp.co.iret.advertisingid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val tag = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            try {
                val info = AdvertisingIdClient.getAdvertisingIdInfo(this@MainActivity)
                if (!info.isLimitAdTrackingEnabled) {
                    Log.d(tag, "AdId: ${info.id}")
                } else {
                    Log.d(tag, "isLimitAdTrackingEnabled: true")
                }
            } catch (e: GooglePlayServicesNotAvailableException) {
                Log.d(tag, e.message)
            } catch (e: GooglePlayServicesRepairableException) {
                Log.d(tag, e.message)
            }
        }
    }
}
