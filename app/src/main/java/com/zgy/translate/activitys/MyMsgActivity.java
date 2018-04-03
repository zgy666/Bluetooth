package com.zgy.translate.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.TimePickerView;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.zgy.translate.R;
import com.zgy.translate.base.BaseActivity;
import com.zgy.translate.controllers.RequestController;
import com.zgy.translate.domains.dtos.UserInfoDTO;
import com.zgy.translate.domains.request.CommonRequest;
import com.zgy.translate.domains.response.CommonResponse;
import com.zgy.translate.global.GlobalConstants;
import com.zgy.translate.global.GlobalParams;
import com.zgy.translate.managers.CacheManager;
import com.zgy.translate.managers.GlideImageManager;
import com.zgy.translate.managers.GsonManager;
import com.zgy.translate.managers.MultiMediaManager;
import com.zgy.translate.managers.UserMessageManager;
import com.zgy.translate.managers.inst.ImageInst;
import com.zgy.translate.utils.ActionSheet;
import com.zgy.translate.utils.ConfigUtil;
import com.zgy.translate.utils.OptItem;
import com.zgy.translate.utils.RedirectUtil;
import com.zgy.translate.utils.StringUtil;
import com.zgy.translate.widget.CommonBar;
import com.zgy.translate.widget.CommonNav;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class MyMsgActivity extends BaseActivity implements CommonBar.CommonBarInterface, ConfigUtil.AlertDialogInterface,
        RequestController.RequestCallInterface{

    private static final String NAME = "name";
    private static final String BIR = "bir";
    private static final String SEX = "sex";
    private static final String ICON = "icon";

    public static final int REQUEST_CODE = 9;//相册选择后返回值
    public static final int PHOTO = 0; //拍照

    @BindView(R.id.amm_cb) CommonBar commonBar;
    @BindView(R.id.amm_civ_headerIcon) CircleImageView civ_headerIcon;
    @BindView(R.id.amm_cn_goPer) CommonNav cn_goPer; //个性签名
    @BindView(R.id.amm_cn_goName) CommonNav cn_goName; //姓名
    @BindView(R.id.amm_cn_goSex) CommonNav cn_goSex; //性别
    @BindView(R.id.amm_cn_goBir) CommonNav cn_goBir; //生日
    @BindView(R.id.amm_cn_goPhone) CommonNav cn_goPhoto; //手机号


    private ImageInst imageInst;
    private Uri photoUri;
    private File photoFile;
    private ActionSheet actionSheet;
    private EditText et_name;
    private TimePickerView timePickerView;
    private String nowDate;
    private CommonRequest request;

    private boolean isIcon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);
        ButterKnife.bind(this);
        super.init();
    }

    @Override
    public void initView() {
        baseInit();
    }

    @Override
    public void initEvent() {
        commonBar.setBarInterface(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void blueOff() {

    }

    @Override
    public void disConnected() {

    }

    @Override
    public void connected() {

    }

    @Override
    public void disNetConnected() {

    }

    @Override
    public void netConnected() {

    }

    @Override
    public void checkLeftIcon() {
        finish();
    }

    @Override
    public void checkRightIcon() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(GlobalParams.userInfoDTO == null){
            GlobalParams.userInfoDTO = UserMessageManager.getUserInfo(this);
        }
        showMsg(GlobalParams.userInfoDTO);
    }

    private void baseInit(){
        imageInst = new ImageInst();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        nowDate = sdf.format(new Date());

        String dataString = nowDate.split(" ")[0];
        int endYear = Integer.valueOf(dataString.split("-")[0]);
        int endMont = Integer.valueOf(dataString.split("-")[1]);
        int endData = Integer.valueOf(dataString.split("-")[2]);

        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);//设置起始年份
        Calendar endDate = Calendar.getInstance();
        endDate.set(endYear, endMont - 1, endData);//设置结束年份

        timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = getTime(date);
                callRequest(BIR, time);
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
//                        .setTitleText("请选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                        .setRangDate(startDate,endDate)//起始终止年月日设定
//                        .setLabel("年","月","日","时","分","秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(true)//是否显示为对话框样式
                .build();
        timePickerView.setDate(Calendar.getInstance());
    }

    /**更换头像*/
    @OnClick(R.id.amm_rl_headerIcoN) void headerIcon(){
        actionSheet = ActionSheet.create(this).setOptItems(
                new OptItem("拍照", getResources().getColor(R.color.colorCommon), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goPhoto();
                        actionSheet.dismiss();
                    }
                }),
                new OptItem("相册", getResources().getColor(R.color.colorCommon), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goImage();
                        actionSheet.dismiss();
                    }
                }));
        actionSheet.show();
    }

    private void goPhoto(){
        photoFile = MultiMediaManager.getFile(CacheManager.getDiskCacheDir(this), getString(R.string.app_name),
                String.valueOf(System.currentTimeMillis()));
        photoUri = imageInst.openCamera(this, photoFile, PHOTO, GlobalConstants.PROVOIDER);
    }

    private void goImage(){
        imageInst.openPhotoAlbum(this, REQUEST_CODE, R.drawable.bg_common_bar);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == PHOTO){
                isIcon = true;
                MultiMediaManager.updateImages(this, photoUri);
                thumbImage();
            }else if(requestCode ==  REQUEST_CODE){
                List<String> path = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
                if(path != null && path.size() > 0){
                    photoFile = new File(path.get(0));
                    isIcon = true;
                    thumbImage();
                }
            }
        }
    }

    private void thumbImage(){
        Luban.with(this)
                .load(photoFile.getAbsoluteFile())
                .ignoreBy(100)
                .setTargetDir(getPath())
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        photoFile = file;
                        callRequest(ICON, null);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/appolo/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    @OnClick(R.id.amm_cn_goPer) void per(){
        RedirectUtil.redirect(this, PersonalizedActivity.class);
    }

    @OnClick(R.id.amm_cn_goName) void name(){
        et_name = new EditText(this);
        et_name.setText(GlobalParams.userInfoDTO.getName());
        ConfigUtil.showAlertDialog(this, "修改姓名", null, et_name, this);
    }

    @Override
    public void confirmDialog() {
        String name = et_name.getText().toString();
        callRequest(NAME, name);
    }

    @Override
    public void cancelDialog() {

    }

    /**性别*/
    @OnClick(R.id.amm_cn_goSex) void sex(){
        actionSheet = ActionSheet.create(this).setOptItems(
                new OptItem("男", R.color.colorCommon, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        actionSheet.dismiss();
                        callRequest(SEX, "MALE");
                    }
                }),
                new OptItem("女", R.color.colorCommon, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        actionSheet.dismiss();
                        callRequest(SEX, "FEMALE");
                    }
                })
        );
        actionSheet.show();
    }

    @OnClick(R.id.amm_cn_goBir) void bir(){
        timePickerView.show();
    }

    @OnClick(R.id.amm_cn_goPhone) void phone(){
        RedirectUtil.redirect(this, RevisePhoneActivity.class);
    }

    /**统一请求*/
    private void callRequest(String tag, String data){
        super.progressDialog.show();
        request = new CommonRequest();
        switch (tag){
            case NAME:
                request.setName(data);
                break;
            case SEX:
                request.setSex(data);
                break;
            case BIR:
                request.setBirthday(data);
                break;
            case ICON:
                request.setFile(photoFile);
                break;
        }
        if(tag.equals(ICON)){
            RequestController.getInstance().init(this)
                    .addRequest(RequestController.CHANGE_ICON, request)
                    .addCallInterface(this).build();
            return;
        }
        RequestController.getInstance().init(this)
                .addRequest(RequestController.PROFILE, request)
                .addCallInterface(this).build();
    }

    @Override
    public void success(CommonResponse response) {
        super.progressDialog.dismiss();
        if(response != null) {
            if(isIcon){
                isIcon = false;
                UserInfoDTO userInfoDTO = UserMessageManager.getUserInfo(this);
                userInfoDTO.setIcon(response.getIcon());
                GlobalParams.userInfoDTO = userInfoDTO;
                String user = GsonManager.getInstance().toJson(userInfoDTO);
                UserMessageManager.deleteUserInfo(this);
                UserMessageManager.saveUserInfo(this, user);
                showMsg(userInfoDTO);
                return;
            }
            if (UserMessageManager.isUserInfo(this)) {
                UserMessageManager.deleteUserInfo(this);
            }
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setAppKey(GlobalParams.userInfoDTO.getAppKey());
            userInfoDTO.setBirthday(response.getBirthday());
            userInfoDTO.setIcon(response.getIcon());
            userInfoDTO.setName(response.getName());
            userInfoDTO.setSignature(response.getSignature());
            userInfoDTO.setSex(response.getSex());
            userInfoDTO.setMic(GlobalParams.userInfoDTO.isMic());
            userInfoDTO.setPhone(response.getPhone());
            GlobalParams.userInfoDTO = userInfoDTO;
            String user = GsonManager.getInstance().toJson(userInfoDTO);
            UserMessageManager.saveUserInfo(this, user);
            showMsg(userInfoDTO);
        }
    }

    @Override
    public void error(CommonResponse response) {
        super.progressDialog.dismiss();
        isIcon = false;
    }

    @Override
    public void fail(String error) {
        super.progressDialog.dismiss();
        isIcon = false;
    }

    /**显示用户信息*/
    private void showMsg(UserInfoDTO dto){
        try {

            if(!StringUtil.isEmpty(dto.getName())){
                cn_goName.setRightTitle(dto.getName());
            }else{
                cn_goName.setRightTitle("用户");
            }
            if(!StringUtil.isEmpty(dto.getIcon())){
                GlideImageManager.showURLDownloadImage(this, dto.getIcon(), civ_headerIcon);
            }
            if(!StringUtil.isEmpty(dto.getSignature())){
                cn_goPer.setRightTitle(dto.getSignature());
            }else{
                cn_goPer.setRightTitle("");
            }
            if(!StringUtil.isEmpty(dto.getBirthday())){
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                //String time = sdf.format(sdf.parse(dto.getBirthday()));
                cn_goBir.setRightTitle(dto.getBirthday());
            }else{
                cn_goBir.setRightTitle(nowDate.split(" ")[0]);
            }
            if(!StringUtil.isEmpty(dto.getSex())){
                switch (dto.getSex()){
                    case "MALE":
                        cn_goSex.setRightTitle("男");
                        break;
                    case "FEMALE":
                        cn_goSex.setRightTitle("女");
                        break;
                    default:
                        cn_goSex.setRightTitle("");
                        break;
                }
            }
            if(!StringUtil.isEmpty(dto.getPhone())){
                cn_goPhoto.setRightTitle(dto.getPhone());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        commonBar.setBarInterface(null);
        if(imageInst != null){
            imageInst.onMyDestroy();
        }
        if(actionSheet != null){
            actionSheet.onMyDestroy();
        }
        if(timePickerView != null){
            timePickerView = null;
        }

        request = null;
        RequestController.getInstance().removeParams();
    }
}
