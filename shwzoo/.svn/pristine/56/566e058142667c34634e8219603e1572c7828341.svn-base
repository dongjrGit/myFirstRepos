/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import com.yinlian.wssc.web.po.Users;

/**
 * SaxParser.java
 * @author Administrator
 * @version $Id: SaxParser.java, v 0.1 2016年3月31日 下午1:08:42 Administrator Exp $
 */
public class SaxParser implements XmlParser {

    /**
     * @see com.yinlian.wssc.web.util.XmlParser#parse(java.io.InputStream)
     */
    @Override
    public List<Users> parse(InputStream is) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance(); // 得到sax解析的工厂
        SAXParser parser = factory.newSAXParser(); // 得到解析器
        XmlHandler handler = new XmlHandler(); // new 一个xml处理器
        parser.parse(is, handler); // 利用xml处理器去解析is流
        return handler.getUserList();//得到集合
    }

    /**
     * @see com.yinlian.wssc.web.util.XmlParser#serialize(java.util.List)
     */
    @Override
    public String serialize(List<Users> list) throws Exception {
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance(); //得到工厂
        TransformerHandler handler = factory.newTransformerHandler();
        Transformer transformer = handler.getTransformer(); //从handler获取Transformer实例  
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // 设置输出采用的编码方式  
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // 
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); // 
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        handler.setResult(result);

        String uri = ""; //代表命名空间的URI 当URI无值时 须置为空字符串  
        String localName = ""; //命名空间的本地名称(不包含前缀) 当没有进行命名空间处理时 须置为空字符串  

        handler.startDocument();
        handler.startElement(uri, localName, "list", null);

        AttributesImpl attrs = new AttributesImpl(); //负责存放元素的属性信息  
        char[] ch = null;
        for (Users user : list) {
            attrs.clear(); //清空属性列表  
            attrs.addAttribute(uri, localName, "id", "string", String.valueOf(user.getId()));//添加一个名为id的属性(type影响不大,这里设为string)  
            handler.startElement(uri, localName, "user", attrs); //开始一个book元素 关联上面设定的id属性  

            handler.startElement(uri, localName, "name", null); //开始一个name元素 没有属性  
            ch = String.valueOf(user.getUsername()).toCharArray();
            handler.characters(ch, 0, ch.length); //设置name元素的文本节点  
            handler.endElement(uri, localName, "name");

            handler.endElement(uri, localName, "user");
        }
        handler.endElement(uri, localName, "list");
        handler.endDocument();

        return writer.toString();
    }

    private class XmlHandler extends DefaultHandler {
        private List<Users>   userList;

        private Users         user;

        private StringBuilder builder;

        /**
         * Getter method for property <tt>userList</tt>.
         * 
         * @return property value of userList
         */
        public List<Users> getUserList() {
            return userList;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            userList = new ArrayList<Users>();
            builder = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                                                                                                   throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (qName.equals("user")) {
                user = new Users();
            }
            builder.setLength(0); //将字符长度设置为0 以便重新开始读取元素内的字符节点  
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            builder.append(ch, start, length); //将读取的字符数组追加到builder中  
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (qName.equals("id")) {
                user.setId(Integer.parseInt(builder.toString()));
            } else if (qName.equals("name")) {
                user.setUsername(builder.toString());
            } else if (qName.equals("user")) {
                userList.add(user);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        List<Users> list = new ArrayList<Users>();
        for (int i = 0; i < 3; i++) {
            Users user = new Users();
            user.setId(i + 1);
            user.setUsername("张三" + i);
            list.add(user);
        }
        SaxParser saxParser = new SaxParser();
        String xml = saxParser.serialize(list);
        System.out.println(xml);

    }
}
