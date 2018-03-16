package com.zgy.translate.global;

import java.util.UUID;

/**
 * Created by zhouguangyue on 2017/11/29.
 */

public class GlobalUUID {

    /*
    ServiceDiscoveryServerServiceClassID_UUID = '{00001000-0000-1000-8000-00805F9B34FB}'
    BrowseGroupDescriptorServiceClassID_UUID = '{00001001-0000-1000-8000-00805F9B34FB}'
    PublicBrowseGroupServiceClass_UUID = '{00001002-0000-1000-8000-00805F9B34FB}'

    蓝牙串口服务
    SerialPortServiceClass_UUID = '{00001101-0000-1000-8000-00805F9B34FB}'
    LANAccessUsingPPPServiceClass_UUID = '{00001102-0000-1000-8000-00805F9B34FB}'

    拨号网络服务
    DialupNetworkingServiceClass_UUID = '{00001103-0000-1000-8000-00805F9B34FB}'

    信息同步服务
    IrMCSyncServiceClass_UUID = '{00001104-0000-1000-8000-00805F9B34FB}'
    SDP_OBEXObjectPushServiceClass_UUID = '{00001105-0000-1000-8000-00805F9B34FB}'

    文件传输服务
    OBEXFileTransferServiceClass_UUID = '{00001106-0000-1000-8000-00805F9B34FB}'
    IrMCSyncCommandServiceClass_UUID = '{00001107-0000-1000-8000-00805F9B34FB}'
    SDP_HeadsetServiceClass_UUID = '{00001108-0000-1000-8000-00805F9B34FB}'
    CordlessTelephonyServiceClass_UUID = '{00001109-0000-1000-8000-00805F9B34FB}'
    SDP_AudioSourceServiceClass_UUID = '{0000110A-0000-1000-8000-00805F9B34FB}'
    SDP_AudioSinkServiceClass_UUID = '{0000110B-0000-1000-8000-00805F9B34FB}'
    SDP_AVRemoteControlTargetServiceClass_UUID = '{0000110C-0000-1000-8000-00805F9B34FB}'
    SDP_AdvancedAudioDistributionServiceClass_UUID = '{0000110D-0000-1000-8000-00805F9B34FB}'
    SDP_AVRemoteControlServiceClass_UUID = '{0000110E-0000-1000-8000-00805F9B34FB}'
    VideoConferencingServiceClass_UUID = '{0000110F-0000-1000-8000-00805F9B34FB}'
    IntercomServiceClass_UUID = '{00001110-0000-1000-8000-00805F9B34FB}'

    蓝牙传真服务
    FaxServiceClass_UUID = '{00001111-0000-1000-8000-00805F9B34FB}'
    HeadsetAudioGatewayServiceClass_UUID = '{00001112-0000-1000-8000-00805F9B34FB}'
    WAPServiceClass_UUID = '{00001113-0000-1000-8000-00805F9B34FB}'
    WAPClientServiceClass_UUID = '{00001114-0000-1000-8000-00805F9B34FB}'

    个人局域网服务
    PANUServiceClass_UUID = '{00001115-0000-1000-8000-00805F9B34FB}'

    个人局域网服务
    NAPServiceClass_UUID = '{00001116-0000-1000-8000-00805F9B34FB}'

    个人局域网服务
    GNServiceClass_UUID = '{00001117-0000-1000-8000-00805F9B34FB}'
    DirectPrintingServiceClass_UUID = '{00001118-0000-1000-8000-00805F9B34FB}'
    ReferencePrintingServiceClass_UUID = '{00001119-0000-1000-8000-00805F9B34FB}'
    ImagingServiceClass_UUID = '{0000111A-0000-1000-8000-00805F9B34FB}'
    ImagingResponderServiceClass_UUID = '{0000111B-0000-1000-8000-00805F9B34FB}'
    ImagingAutomaticArchiveServiceClass_UUID = '{0000111C-0000-1000-8000-00805F9B34FB}'
    ImagingReferenceObjectsServiceClass_UUID = '{0000111D-0000-1000-8000-00805F9B34FB}'
    SDP_HandsfreeServiceClass_UUID = '{0000111E-0000-1000-8000-00805F9B34FB}'
    HandsfreeAudioGatewayServiceClass_UUID = '{0000111F-0000-1000-8000-00805F9B34FB}'
    DirectPrintingReferenceObjectsServiceClass_UUID = '{00001120-0000-1000-8000-00805F9B34FB}'
    ReflectedUIServiceClass_UUID = '{00001121-0000-1000-8000-00805F9B34FB}'
    BasicPringingServiceClass_UUID = '{00001122-0000-1000-8000-00805F9B34FB}'
    PrintingStatusServiceClass_UUID = '{00001123-0000-1000-8000-00805F9B34FB}'

    人机输入服务
    HumanInterfaceDeviceServiceClass_UUID = '{00001124-0000-1000-8000-00805F9B34FB}'

    HardcopyCableReplacementServiceClass_UUID = '{00001125-0000-1000-8000-00805F9B34FB}'

    蓝牙打印服务
    HCRPrintServiceClass_UUID = '{00001126-0000-1000-8000-00805F9B34FB}'
    HCRScanServiceClass_UUID = '{00001127-0000-1000-8000-00805F9B34FB}'
    CommonISDNAccessServiceClass_UUID = '{00001128-0000-1000-8000-00805F9B34FB}'
    VideoConferencingGWServiceClass_UUID = '{00001129-0000-1000-8000-00805F9B34FB}'
    UDIMTServiceClass_UUID = '{0000112A-0000-1000-8000-00805F9B34FB}'
    UDITAServiceClass_UUID = '{0000112B-0000-1000-8000-00805F9B34FB}'
    AudioVideoServiceClass_UUID = '{0000112C-0000-1000-8000-00805F9B34FB}'
    SIMAccessServiceClass_UUID = '{0000112D-0000-1000-8000-00805F9B34FB}'
    PnPInformationServiceClass_UUID = '{00001200-0000-1000-8000-00805F9B34FB}'
    GenericNetworkingServiceClass_UUID = '{00001201-0000-1000-8000-00805F9B34FB}'
    GenericFileTransferServiceClass_UUID = '{00001202-0000-1000-8000-00805F9B34FB}'
    GenericAudioServiceClass_UUID = '{00001203-0000-1000-8000-00805F9B34FB}'
    GenericTelephonyServiceClass_UUID = '{00001204-0000-1000-8000-00805F9B34FB}'*/

    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final UUID MY_UUID2 = UUID.fromString("85cd6bc5-6ae9-e30b-7cac-4db2e0dad51a");


}
