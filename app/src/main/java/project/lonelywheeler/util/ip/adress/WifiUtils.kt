package project.lonelywheeler.util.ip.adress

import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.text.format.Formatter
import project.lonelywheeler.app.MyApplication.Companion.context


object WifiUtils {

    /**
     * Get IP address from first non-localhost interface
     * @param useIPv4   true=return ipv4, false=return ipv6
     * @return  address or empty string
     */
/*    fun getLocalIPV4(useIPv4: Boolean): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress) {
                        return inetAddress.hostAddress
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e("IP Address", ex.toString())
        }
        return null
    }*/

    fun getLocalIPv4() : String{
        val wifiManager = context().getSystemService(
            WIFI_SERVICE
        )as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        val ipAddress: String = Formatter.formatIpAddress(wifiInfo.ipAddress)
        return ipAddress
    }
}