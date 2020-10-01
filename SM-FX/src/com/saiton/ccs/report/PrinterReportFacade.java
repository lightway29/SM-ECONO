package com.saiton.ccs.report;

import com.saiton.ccs.reportdao.PrinterReportDAO;
import java.util.ArrayList;
import java.util.List;


public class PrinterReportFacade {

    private static final PrinterReportDAO prdao = new PrinterReportDAO();

    //--------------------------------------------------------
    //PRINTER
    public List<String> selectAllPrinterTypes() {
        return prdao.selectAllPrinterTypes();
    }

    public String generatePrinterId() {
        return prdao.generatePrinterId();
    }

    public Boolean insertPrinter(String name, String type, String description) {

        return prdao.insertPrinter(name, type, description);
    }

    public Boolean updatePrinter(String pid, String name, String type,
            String description) {

        return prdao.updatePrinter(pid, name, type, description);
    }

    public Boolean deletePrinter(String pid) {
        return prdao.deletePrinter(pid);
    }

    public List<Printer> selectAllPrinters() {
        List<String[]> data = prdao.selectAllPrinters();
        if (data == null) {
            return null;
        }
        ArrayList<Printer> printers = new ArrayList<>();
        for (String[] atom : data) {
            Printer printer = new Printer();
            printer.setFromStringArray(atom);
            printers.add(printer);
        }
        return printers;
    }

    //----------------------------------------------------
    //REPORT
    public List<String> selectAllReportTypes() {
        return prdao.selectAllReportTypes();
    }

    public String generateReportId() {
        return prdao.generateReportId();
    }

    public Boolean insertReport(String name, String type) {

        return prdao.insertReport(name, type);
    }

    public Boolean updateReport(String rid, String name, String type) {

        return prdao.updateReport(rid, name, type);
    }

    public Boolean deleteReport(String rid) {
        return prdao.deleteReport(rid);
    }

    public List<Report> selectAllReports() {
        List<String[]> data = prdao.selectAllReports();
        if (data == null) {
            return null;
        }
        ArrayList<Report> reports = new ArrayList<>();
        for (String[] atom : data) {
            Report report = new Report();
            report.setFromStringArray(atom);
            reports.add(report);
        }
        return reports;

    }

    //------------------------------------------------------
    //PRINTER REPORT
    public List<PrinterReport> selectAllPrinterReports() {
        List<String[]> data = prdao.selectAllPrinterReports();
        if (data == null) {
            return null;
        }
        ArrayList<PrinterReport> prs = new ArrayList<>();
        for (String[] atom : data) {
            PrinterReport pr = new PrinterReport();
            pr.setFromStringArray(atom);
            prs.add(pr);
        }
        return prs;
    }

    public Boolean setAllPrinterReports(List<PrinterReport> data) {
        if (data == null) {
            return false;
        }
        ArrayList<String[]> toSend = new ArrayList<>();
        
        for(PrinterReport pr:data){
            toSend.add(pr.toStringArray());
        }
        return prdao.setAllPrinterReports(toSend);
    }
}
