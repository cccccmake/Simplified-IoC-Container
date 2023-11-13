package com.imooc.spring.ioc.context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ClassPathXmlApplicationContext implements ApplicationContext{
    private Map iocContainer = new HashMap();
    public ClassPathXmlApplicationContext(){
        try{
            // System.out.println(this.getClass());
            // -> class com.imooc.spring.ioc.context.ClassPathXmlApplicationContext
            String filePath = this.getClass().getResource("/applicationContext.xml").getPath();
            filePath = URLDecoder.decode(filePath, "UTF-8");
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(filePath));
            List<Node> beans = document.getRootElement().selectNodes("bean");
            for(Node node : beans){
                Element element = (Element)node;
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                Class c = Class.forName(className);
                Object obj = c.newInstance();
                List<Node> properties = element.selectNodes("property");
                for(Node p : properties){
                    Element property = (Element) p;
                    String propertyName = property.attributeValue("name");
                    String propertyValue = property.attributeValue("value");
                    String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                    Method setMethod = c.getMethod(setMethodName, String.class);
                    setMethod.invoke(obj, propertyValue); // setter inject data
                }
                iocContainer.put(id, obj);
            }
            System.out.println("Init IoC");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Object getBean(String beanId) {
        return iocContainer.get(beanId);
    }
}
