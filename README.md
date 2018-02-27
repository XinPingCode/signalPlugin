# signalPlugin
a cordova plugin to get signal  information only for android.

cordova.plugins.signalPlugin.signalStrength(1,result =>{
//result=lte_sinr.toString()+","+lte_rsrp.toString()+","+lte_rsrq.toString();
},error =>alert(error));

cordova.plugins.signalPlugin.signal(1,result =>{
//result= ""+mcc_s +","+ mnc_s+ "," + lac_s + "," + cellId_s+ ","+imei_s+","+imsi_s;
},error =>alert(error));

cordova.plugins.signalPlugin.getServerCellInfo(1,result =>{
//result=Sinfo=CIdl_s+","+pcil_s+","+tacl_s+","+rsrpl_s+","+asulevell_s+","+RatTypel_s+","+CIdw_s+","+pciw_s+","+tacw_s+","+rsrpw_s+","+asulevelw_s+","+RatTypew_s+","
                +CIdg_s+","+pcig_s+","+tacg_s+","+rsrpg_s+","+asulevelg_s+","+RatTypeg_s+alli_s;
},error =>alert(error));

cordova.plugins.signalPlugin.getPhone(1,result =>{
//result=model+","+carrier;
},error =>alert(error));

attention：signalStrength() only be use in the second time can get information. 
