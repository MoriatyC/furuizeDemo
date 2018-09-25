package model;

/**
 * demo 用的model，正式环境中根据需要，将271维数据封装到1个或多个model中
 */
public class EasyModel {
    /**
     * 近3个月贷款审批和信用卡审批次数
     */
    private int app_all_inq_lst3mth;
    /**
     * 主贷人过去12个月逾期期数（曾经）达到过或超过过1期的贷记卡总张数
     */
    private int app_card_cnt_ever_1p_del_lst12mth;
    /**
     * 主贷人过去24个月逾期期数（曾经）达到过或超过过1期的贷记卡总张数
     */
    private int app_card_cnt_ever_1p_del_lst24mth;
    /**
     *     主贷人过去6个月逾期期数（曾经）达到过或超过过1期的贷记卡总张数
     */
    private int app_card_cnt_ever_1p_del_lst6mth;

    public int getApp_all_inq_lst3mth() {
        return app_all_inq_lst3mth;
    }

    public void setApp_all_inq_lst3mth(int app_all_inq_lst3mth) {
        this.app_all_inq_lst3mth = app_all_inq_lst3mth;
    }

    public int getApp_card_cnt_ever_1p_del_lst12mth() {
        return app_card_cnt_ever_1p_del_lst12mth;
    }

    public void setApp_card_cnt_ever_1p_del_lst12mth(int app_card_cnt_ever_1p_del_lst12mth) {
        this.app_card_cnt_ever_1p_del_lst12mth = app_card_cnt_ever_1p_del_lst12mth;
    }

    public int getApp_card_cnt_ever_1p_del_lst24mth() {
        return app_card_cnt_ever_1p_del_lst24mth;
    }

    public void setApp_card_cnt_ever_1p_del_lst24mth(int app_card_cnt_ever_1p_del_lst24mth) {
        this.app_card_cnt_ever_1p_del_lst24mth = app_card_cnt_ever_1p_del_lst24mth;
    }

    public int getApp_card_cnt_ever_1p_del_lst6mth() {
        return app_card_cnt_ever_1p_del_lst6mth;
    }

    public void setApp_card_cnt_ever_1p_del_lst6mth(int app_card_cnt_ever_1p_del_lst6mth) {
        this.app_card_cnt_ever_1p_del_lst6mth = app_card_cnt_ever_1p_del_lst6mth;
    }
}
