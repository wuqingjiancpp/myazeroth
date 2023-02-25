package com.accendl.azeroth.util;

import com.accendl.azeroth.service.impl.AccountServiceImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DomUtils {
    private static final Logger logger = LoggerFactory.getLogger(DomUtils.class);

    public static String parseHttpContent(String content) throws DocumentException {
        Document document = DocumentHelper.parseText(content);
        Node resultNode = document.selectSingleNode("//SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:executeCommandResponse/result");
        if (resultNode != null){
            //Account created: test3
            String result = resultNode.getText();
            logger.error(result);
            return result;
        }else{
            Node faultNode = document.selectSingleNode("//SOAP-ENV:Envelope/SOAP-ENV:Body/SOAP-ENV:Fault");
            String faultCode = faultNode.getDocument().selectSingleNode("//faultcode").getText();
            String faultString = faultNode.getDocument().selectSingleNode("//faultstring").getText();
            String detail = faultNode.getDocument().selectSingleNode("//detail").getText();

            logger.error(faultCode);
            logger.error(faultString);
            //Account with this name already exist!
            logger.error(detail);

            return detail;
        }
    }
}
