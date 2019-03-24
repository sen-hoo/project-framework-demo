package com.cc.framework.springboot.web.controllers;

import com.cc.framework.springboot.web.version.RequestVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "testemay")
public class TestEmayReportController {

    @RequestMapping(value = "{v}/getReport", produces = "text/xml")
    @RequestVersion(vNo = 1.0F)
    public String simulateReports() {
        String ret = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<response>" +
                "<error>0</error>" +
                "<message><srctermid>18600002890</srctermid><submitDate>2017-03-24 12:00:00</submitDate><receiveDate>2017-03-24 12:01:00</receiveDate><addSerial>112121</addSerial><addSerialRev>123</addSerialRev><state>FAILED</state><seqid>1234</seqid></message>" +
                "<message><srctermid>18600002890</srctermid><submitDate>2017-03-24 12:00:00</submitDate><receiveDate>2017-03-24 12:01:00</receiveDate><addSerial>112121</addSerial><addSerialRev>123</addSerialRev><state>FAILED</state><seqid>1234</seqid></message>" +
                "<message><srctermid>18600002890</srctermid><submitDate>2017-03-24 12:00:00</submitDate><receiveDate>2017-03-24 12:01:00</receiveDate><addSerial>112121</addSerial><addSerialRev>123</addSerialRev><state>FAILED</state><seqid>1234</seqid></message>" +
                "<message><srctermid>18600002890</srctermid><submitDate>2017-03-24 12:00:00</submitDate><receiveDate>2017-03-24 12:01:00</receiveDate><addSerial>112121</addSerial><addSerialRev>123</addSerialRev><state>FAILED</state><seqid>1234</seqid></message>" +
                "<message><srctermid>18600002890</srctermid><submitDate>2017-03-24 12:00:00</submitDate><receiveDate>2017-03-24 12:01:00</receiveDate><addSerial>112121</addSerial><addSerialRev>123</addSerialRev><state>FAILED</state><seqid>1234</seqid></message>" +
                "<message><srctermid>18600002890</srctermid><submitDate>2017-03-24 12:00:00</submitDate><receiveDate>2017-03-24 12:01:00</receiveDate><addSerial>112121</addSerial><addSerialRev>123</addSerialRev><state>FAILED</state><seqid>1234</seqid></message>" +
                "<message><srctermid>18600002890</srctermid><submitDate>2017-03-24 12:00:00</submitDate><receiveDate>2017-03-24 12:01:00</receiveDate><addSerial>112121</addSerial><addSerialRev>123</addSerialRev><state>FAILED</state><seqid>1234</seqid></message>" +
                "<message><srctermid>18600002890</srctermid><submitDate>2017-03-24 12:00:00</submitDate><receiveDate>2017-03-24 12:01:00</receiveDate><addSerial>112121</addSerial><addSerialRev>123</addSerialRev><state>FAILED</state><seqid>1234</seqid></message>" +
                "</response>";
        return ret;
    }

}
