package signalPlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;

import java.util.List;  

import android.app.Activity;  
import android.content.Context;  
import android.os.Bundle;  
import android.telephony.NeighboringCellInfo;  
import android.telephony.TelephonyManager;  
import android.telephony.cdma.CdmaCellLocation;  
import android.telephony.gsm.GsmCellLocation; 
import android.telephony.CellInfo; 
import android.telephony.CellInfoLte;  
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellInfoWcdma; 
import android.telephony.CellInfoGsm; 
import android.telephony.PhoneStateListener;
import  android.telephony.SignalStrength;
import android.util.Log;  
import android.view.View; 

import android.content.Context; 


/**
 * This class echoes a string called from JavaScript.
 */
public class signalPlugin extends CordovaPlugin {

  public String s1="233";
  public Integer lte_sinr=0;
  public Integer lte_rsrp=0;
  public Integer lte_rsrq=0;
  public Integer lte_rssnr=0;
  public Integer lte_cqi=0;
  public String lte_rsrq_s="h";
  public int lte_rsrq_i=0;
  
  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    if (action.equals("coolMethod")) {
        String message = args.getString(0);
        this.coolMethod(message, callbackContext);
        return true;
    }else if(action.equals("signal")) {

        String message = args.getString(0);
        this.signal(message,callbackContext);
        return true;
    }else if(action.equals("getServerCellInfo")) {

        String message = args.getString(0);
        this.getServerCellInfo(message,callbackContext);
        return true;
    }else if(action.equals("signalStrength")) {

        String message = args.getString(0);
        this.signalStrength(message,callbackContext);
        return true;
    }else if(action.equals("getPhone")) {

        String message = args.getString(0);
        this.getPhone(message,callbackContext);
        return true;
    }
    return false;
}

private void coolMethod(String message, CallbackContext callbackContext) {
    if (message != null && message.length() > 0) {
        callbackContext.success(message);
    } else {
        callbackContext.error("Expected one non-empty string argument.");
    }
}
private void getPhone(String message, CallbackContext callbackContext){
    String   model= android.os.Build.MODEL;
    String carrier= android.os.Build.MANUFACTURER;
    String version=android.os.Build.VERSION.SDK;
    String x1=model+","+carrier+","+version;
    callbackContext.success(x1);
}
private void signal(String message, CallbackContext callbackContext){
        /** 
 * 功能描述：通过手机信号获取基站信息 
 * # 通过TelephonyManager 获取lac:mcc:mnc:cell-id 
 * # MCC，Mobile Country Code，移动国家代码（中国的为460）； 
 * # MNC，Mobile Network Code，移动网络号码（中国移动为0，中国联通为1，中国电信为2）；  
 * # LAC，Location Area Code，位置区域码； 
 * # CID，Cell Identity，基站编号； 
 * # BSSS，Base station signal strength，基站信号强度。 
 * 
 */  





        //Context context = this.getContext();
        //context = context.getApplicationContext();
        Context context = this.cordova.getActivity().getApplicationContext();
        TelephonyManager mTelephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);  
          //获取手机IMEI
        String imei_s = ""+mTelephonyManager.getDeviceId();
        //获取手机IMSI
        String imsi_s = ""+mTelephonyManager.getSubscriberId();
                // 返回值MCC + MNC  
        String operator = mTelephonyManager.getNetworkOperator();  
        int mcc = Integer.parseInt(operator.substring(0, 3));  
        int mnc = Integer.parseInt(operator.substring(3));  
        String mcc_s = ""+String.valueOf(mcc);
        String mnc_s = ""+String.valueOf(mnc);

                // 中国移动和中国联通获取LAC、CID的方式  
        GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();  
        int lac = location.getLac();  
        int cellId = location.getCid();  
        String lac_s = ""+String.valueOf(lac);
        String cellId_s = ""+String.valueOf(cellId);

        //Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);  

                // 中国电信获取LAC、CID的方式  
                /*CdmaCellLocation location1 = (CdmaCellLocation) mTelephonyManager.getCellLocation(); 
                lac = location1.getNetworkId(); 
                cellId = location1.getBaseStationId(); 
                cellId /= 16;*/  

              
                String signalString = ""+mcc_s +","+ mnc_s+ "," + lac_s + "," + cellId_s+ ","+imei_s+","+imsi_s;
                // Toast.makeText(cordova.getActivity(), signalString, Toast.LENGTH_SHORT).show();
                callbackContext.success(signalString);
            }
            private void getServerCellInfo(String message, CallbackContext callbackContext)  
            {  
                int pcig=0;
                int pciw=0;
                int pcil=0;
                int tacg=0;
                int tacw=0;
                int tacl=0;
                int rsrpg=0;
                int rsrpw=0;
                int rsrpl=0;
                int rsrqg=0;
                int rsrqw=0;
                int rsrql=0;
                int asulevelg=0;
                int asulevelw=0;
                int asulevell=0;
                int RatTypeg=0;
                int RatTypew=0;
                int RatTypel=0;
                int CIdg=0;
                int CIdw=0;
                int CIdl=0;
                int alli=0;
                int bssss=0;
                int cqil=0;
                int rssnrl=0;
                String pcig_s="";
                String pciw_s="";
                String pcil_s="";
                String tacg_s="";
                String tacw_s="";
                String tacl_s="";
                String rsrpg_s="";
                String rsrpw_s="";
                String rsrpl_s="";
                String asulevelg_s="";
                String asulevelw_s="";
                String asulevell_s="";
                String RatTypeg_s="";
                String RatTypew_s="";
                String RatTypel_s="";
                String CIdg_s="";
                String CIdw_s="";
                String CIdl_s="";
                String alli_s="";
                String rsrql_s="";
                String rsrqw_s="";
                String rsrqg_s="";
                String cqil_s="";
                String rssnrl_s="";
                try  
                {    Context context = this.cordova.getActivity().getApplicationContext();
                    TelephonyManager mTelephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);    
                    List<CellInfo> allCellinfo;  
                    allCellinfo = mTelephonyManager.getAllCellInfo();  
                    // alli=allCellinfo.length;
                    //  alli_s=String.valueOf(alli);
                    for (CellInfo info : allCellinfo) {
        // CellInfoCdma cellInfoCdma = (CellInfoCdma) info;
        // CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
        // CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
        // int strength = cellSignalStrengthCdma.getCdmaDbm();
        // int cid = cellIdentityCdma.getBasestationId();// 处理 strength和id数据
                        alli++;

                    }
                    alli_s=String.valueOf(alli);
                    if (allCellinfo != null)  
                    {  

                        CellInfo cellInfo = allCellinfo.get(0);  
                        //serverCellInfo.getInfoType = 1;  
                        if (cellInfo instanceof CellInfoGsm)  
                        {  
                            //Toast.makeText(cordova.getActivity(), "GSM", Toast.LENGTH_SHORT).show();
                            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;  
                            CIdg = cellInfoGsm.getCellIdentity().getCid();  
                            rsrpg = cellInfoGsm.getCellSignalStrength().getDbm();  
                            asulevelg = cellInfoGsm.getCellSignalStrength().getAsuLevel();  

                        //serverCellInfo.lac = cellInfoGsm.getCellIdentity().getLac();  
                            // RatTypeg = TelephonyManager.NETWORK_TYPE_GSM;  
                            CIdg_s=String.valueOf(CIdg);  
                            pcig_s=String.valueOf(pcig); 
                            tacg_s=String.valueOf(tacg); 
                            rsrpg_s=String.valueOf(rsrpg); 
                            asulevelg_s=String.valueOf(asulevelg); 
                            // RatTypeg_s=String.valueOf(RatTypeg); 
                        } else if (cellInfo instanceof CellInfoWcdma)  
                        {  
                            // Toast.makeText(cordova.getActivity(), "WCDMA", Toast.LENGTH_SHORT).show();
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;  
                            CIdw = cellInfoWcdma.getCellIdentity().getCid();  
                        // psc = cellInfoWcdma.getCellIdentity().getPsc();  
                        // serverCellInfo.lac = cellInfoWcdma.getCellIdentity().getLac();  
                            rsrpw = cellInfoWcdma.getCellSignalStrength().getDbm();  
                            asulevelw = cellInfoWcdma.getCellSignalStrength().getAsuLevel();  
                            RatTypew = TelephonyManager.NETWORK_TYPE_UMTS;  
                            CIdw_s=String.valueOf(CIdw);  
                            pciw_s=String.valueOf(pciw); 
                            tacw_s=String.valueOf(tacw); 
                            rsrpw_s=String.valueOf(rsrpw); 
                            asulevelw_s=String.valueOf(asulevelw); 
                            RatTypew_s=String.valueOf(RatTypew); 
                        } else if (cellInfo instanceof CellInfoLte)  
                        {  
                        //Toast.makeText(cordova.getActivity(), "LTE", Toast.LENGTH_SHORT).show();
                            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;  
                            CIdl = cellInfoLte.getCellIdentity().getCi();  
                            pcil = cellInfoLte.getCellIdentity().getPci();  
                            tacl = cellInfoLte.getCellIdentity().getTac();  
                            rsrpl = cellInfoLte.getCellSignalStrength().getDbm();  
                            asulevell = cellInfoLte.getCellSignalStrength().getAsuLevel();  
                             // rsrql=cellInfoLte.getCellSignalStrength().getRsrq();
                             // cqil=cellInfoLte.getCellSignalStrength().getCqi();
                             // rssnrl=cellInfoLte.getCellSignalStrength().getRssnr();
                            // CellSignalStrengthLte rsrq2=cellInfoLte.getCellSignalStrength();
                            // rsrql=rsrq2.getRsrq();
                            //"rsrq"+rsrql_s+"cqi"+cqil_s+"rssnr"+rssnrl_s
                            RatTypel = TelephonyManager.NETWORK_TYPE_LTE;  
                            CIdl_s=String.valueOf(CIdl);  
                            pcil_s=String.valueOf(pcil); 
                            tacl_s=String.valueOf(tacl); 
                            rsrpl_s=String.valueOf(rsrpl); 
                            // rsrql_s=String.valueOf(rsrql);
                            asulevell_s=String.valueOf(asulevell); 
                            RatTypel_s=String.valueOf(RatTypel); 
                        }  
                    }  
                    else  
                //for older devices  
                    {  
                        //getServerCellInfoOnOlderDevices();  
                        Toast.makeText(cordova.getActivity(), "OLD", Toast.LENGTH_SHORT).show();
                    }  
                }  
                catch(Exception e)  
                {  
                    // getServerCellInfoOnOlderDevices();  
                    Toast.makeText(cordova.getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }  
                String Sinfo=CIdl_s+","+pcil_s+","+tacl_s+","+rsrpl_s+","+asulevell_s+","+RatTypel_s+","+CIdw_s+","+pciw_s+","+tacw_s+","+rsrpw_s+","+asulevelw_s+","+RatTypew_s+","
                +CIdg_s+","+pcig_s+","+tacg_s+","+rsrpg_s+","+asulevelg_s+","+RatTypeg_s+alli_s;
                callbackContext.success(Sinfo);

            }  
            public void signalStrength(String message, CallbackContext callbackContext){




             Context context = this.cordova.getActivity().getApplicationContext();

             final TelephonyManager telmanager= (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);  
             List<CellInfo> allCellinfoq;  
             int allq=0;
             allCellinfoq = telmanager.getAllCellInfo();  

             for (CellInfo info : allCellinfoq) {

                allq++;

            }

            if (allCellinfoq!= null)  
            {  

                CellInfo cellInfoq = allCellinfoq.get(0);  
                        //serverCellInfo.getInfoType = 1;  
                if (cellInfoq instanceof CellInfoLte)  
                {  
                 PhoneStateListener listen= new PhoneStateListener() {
                    public void onSignalStrengthsChanged(SignalStrength signalStrength) {

                     super.onSignalStrengthsChanged(signalStrength);
                     try {
                        lte_sinr = (Integer) signalStrength.getClass().getMethod("getLteSignalStrength").invoke(signalStrength);
                        lte_rsrp = (Integer) signalStrength.getClass().getMethod("getLteRsrp").invoke(signalStrength);
                        lte_rsrq = (Integer) signalStrength.getClass().getMethod("getLteRsrq").invoke(signalStrength);
                        lte_rssnr = (Integer) signalStrength.getClass().getMethod("getLteRssnr").invoke(signalStrength);
                        lte_cqi = (Integer) signalStrength.getClass().getMethod("getLteCqi").invoke(signalStrength);

                     // lte_rsrq = (Integer)signalStrength.getClass().getMethod("getLteRsrq").invoke(signalStrength);
                     // lte_rsrq_i=lte_rsrq.intValue();
                     // lte_rsrq_s=String.valueOf(lte_rsrq_i); 

                    } catch (Exception e) {
                          //e.printStackTrace();
                        Toast.makeText(cordova.getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            };
            //+","+"RSSNR:"+lte_rssnr.toString()+","+"CQI:"+lte_cqi.toString()
            s1=lte_sinr.toString()+","+lte_rsrp.toString()+","+lte_rsrq.toString();
            telmanager.listen(listen, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        }else{
            s1="NoLTE";
        }
    }else{
        s1="NoNet";
    }
    callbackContext.success(s1);
}
}
