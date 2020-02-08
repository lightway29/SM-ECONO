/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.printerservice;

/**
 *
 * @author nadeesha
 */
public enum ReportPath {

    PATH_BANQUET_KOT_REPORT(".//Reports//KOTBanquet.jasper"),
    PATH_BANQUET_BOT_REPORT(".//Reports//BOTBanquet.jasper"),
    PATH_ADVANCE_RECEIPT(".//Reports//AdvanceReceipt.jasper"),
    PATH_BANQUET_MAIN_BILL(".//Reports//BanquetMainBill.jasper"),
    PATH_ALACARTE_BOT_REPORT(".//Reports//BOTAlacarte.jasper"),
    PATH_ROOM_BOT_REPORT(".//Reports//BOTRoom.jasper"),
    PATH_EXTERNAL_GOOD_RECEIVED_NOTE(
            ".//Reports//ExternalGoodReceivedNote.jasper"),
    PATH_ROOM_DAILY_RESERVATION_REPORT(
            ".//Reports//DailyReservationSummary.jasper"),
    PATH_FUNCTION_SHEET_MAIN(".//Reports//FunctionSheetMain.jasper"),
    PATH_FUNCTION_SHEET_SUBREPORT_1(".//Reports//FunctionSheetSub1.jasper"),
    PATH_FUNCTION_SHEET_SUBREPORT_2(".//Reports//FunctionSheetSub2.jasper"),
    PATH_INTERNAL_GOOD_RECEIVED_NOTE(
            ".//Reports//InternalGoodReceivedNote.jasper"),
    PATH_ALACARTE_KOT_REPORT(".//Reports//KOTAlacarte.jasper"),
    PATH_ROOM_KOT_REPORT(".//Reports//KOTRoom.jasper"),
    PATH_MAIN_BILL_ALACART_BOT_REPORT(".//Reports//MainBillAlacartBOT.jasper"),
    PATH_MAIN_BILL_ALACART_KOT_REPORT(".//Reports//MainBillAlacartKOT.jasper"),
    PATH_MAIN_BILL_BANQUET_BOT_REPORT(".//Reports//MainBillBanquetBOT.jasper"),
    PATH_MAIN_BILL_BANQUET_KOT_REPORT(".//Reports//MainBillBanquetKOT.jasper"),
    PATH_MAIN_BILL_ROOM_BOT_REPORT(".//Reports//MainBillRoomBOT.jasper"),
    PATH_MAIN_BILL_ROOM_KOT_REPORT(".//Reports//MainBillRoomKOT.jasper"),
    PATH_NATIONALITY(".//Reports//Nationality.jasper"),
    PATH_NATIONALITY_BY_COUNTRY(".//Reports//Nationality_by_country.jasper"),
    PATH_NO_SHOW(".//Reports//NoShow.jasper"),
    PATH_PURCHASE_ORDER_SHEET(".//Reports//PurchaseOrderSheet.jasper"),
    PATH_REGISTRATION_CARD(".//Reports//RegistrationCard.jasper"),
    PATH_REGISTRATION_CARD_GROUP(".//Reports//RegistrationCardGroup.jasper"),
    PATH_RESERVATION_CARD(".//Reports//ReservationCard.jasper"),
    PATH_RESERVATION_CARD_GROUP(".//Reports//ReservationCardGroup.jasper"),
    PATH_ROOM_MASTER_BILL_MAIN_REPORT_CUS(".//Reports//RoomMasterBillMain.jasper"),
    PATH_ROOM_MASTER_BILL_MAIN_REPORT_AGENT(
            ".//Reports//RoomMasterBillMainAgent.jasper"),
    PATH_ROOM_SALES_BY_ROOM_NO_REPORT(".//Reports//RoomSalesByRoomNo.jasper"),
    PATH_STORES_ISSUE_NOTE(".//Reports//StoresIssueNote.jasper"),
    PATH_STORES_REQUISITION_REPORT(".//Reports//StoresRequisition.jasper"),
    PATH_SUNDRY_BILL(".//Reports//SundryBill.jasper"),
    PATH_SUNDRY_BILL_WITHOUT_PAYMENT(".//Reports//SundryBillMain.jasper"),
    PATH_ISSUE_NOTE_OVERVIEW(".//Reports//StoresIssueNote.jasper"),
    PATH_PURCASE_ORDER_OVERVIEW(".//Reports//PurchaseOrderSheet.jasper"),
    PATH_MAINBILL_ALACARTE_EVENT(".//Reports//MainBillAlacart_Event.jasper"),
    PATH_MAINBILL_ALACARTE(".//Reports//MainBillAlacart.jasper"),
    PATH_MAINBILL_ALACARTE_TEMPORARY(
            ".//Reports//MainBillAlacartTemporary.jasper"),
    PATH_MAINBILL_ALACARTE_TEMPORARY_SPLIT(
            ".//Reports//MainBillAlacartTemporarySplit.jasper"),
    PATH_MAINBILL_ALACARTE_SPLIT(".//Reports//MainBillAlacartSplit.jasper"),
    PATH_ADVANCE_RECEIPT_RESERVATION(
            ".//Reports//AdvanceReceiptReservation.jasper"),
    PATH_CUSTOMER_ADVANCE_RECEIPT(".//Reports//CustomerAdvanceReceipt.jasper"),
    PATH_LAUNDRY_BILL_MAIN(".//Reports//LaundryBillMain.jasper"),
    PATH_LAUNDRY_BILL_SUNDRY(".//Reports//LaundryBillSundry.jasper"),
    PATH_AGENT_RATE_REGISTRATION_AGREEMENT(
            ".//Reports//TravelAgentAgreement.jasper"),
    PATH_CUSTOMER_RECEIPT(".//Reports//CustomerReceipt.jasper"),
    PATH_INTERNAL_RETURN_NOTE(".//Reports//InternalReturnNote.jasper"),
    PATH_TRAVEL_AGENT_PERFORM_INVOICE_REPORT(
            ".//Reports//TravelAgentProfomaInvoice.jasper"),
    PATH_EXTERNAL_RETURN_NOTE(".//Reports//ExternalReturnNote.jasper"),
    PATH_ROOM_STOCK(".//Reports//StockRoom.jasper"),
    PATH_BANQUET_STOCK(".//Reports//StockBanquet.jasper"),
    PATH_ALACARTE_STOCK(".//Reports//StockAlacarte.jasper"),
    PATH_MAIN_STOCK(".//Reports//StockMain.jasper"),
    PATH_ALACARTE_KOT_BOT_SALES_REPORT(
            ".//Reports//AlacarteKotBotSummary.jasper"),
    PATH_ALACARTE_KOT_CANCEL_REPORT(".//Reports//KOTAlacarteCancel.jasper"),
    PATH_ALACARTE_BOT_CANCEL_REPORT(".//Reports//BOTAlacarteCancel.jasper"),
    PATH_ALARCATE_FOOD_LISTING_REPORT(
            ".//Reports//AlacarteOrderItemSales.jasper"),
    PATH_ALACARTE_CREDIT_SETTLEMENT_RECEIPT(
            ".//Reports//AlacarteCreditSettlementReceipt.jasper"),
    PATH_RESERVATION_CANCEL_REPORT(".//Reports//ReservationCancel.jasper"),
    PATH_RESERVATION_GROUP_CANCEL_REPORT(
            ".//Reports//ReservationGroupCancel.jasper"),
    PATH_FUNCTION_REGISTRATION_REPORT(".//Reports//FunctionReceipt.jasper"),
    PATH_WEIGHT_ONE_REPORT(".//Reports//WeightScale1.jasper"),
    PATH_BARCODE_REPORT(".//Reports//Barcode.jasper"),
    PATH_WEIGHT_TWO_REPORT(".//Reports//WeightScale2.jasper");

    private final String val;

    private ReportPath(String val) {

        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
