package com.sample.framework.ui;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageFactory {

    public PageFactory() {
        // TODO Auto-generated constructor stub
    }

    private static By toLocator(String input) {
        if (input.matches("^(xpath=|/)(.*)")) {
            return By.xpath(input.replaceAll("^xpath=", ""));
        } else if (input.matches("^id=(.*)")) {
            return By.id(input.substring("id=".length()));
        } else if (input.matches("^name=(.*)")) {
            return By.name(input.substring("name=".length()));
        } else if (input.matches("^css=(.*)")) {
            return By.cssSelector(input.substring("css=".length()));
        } else if (input.matches("^class=(.*)")) {
            return By.className(input.substring("class=".length()));
        } else {
            return By.id(input);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T getAnnotationField(Annotation annotation, String name,
            Class<T> type) throws Exception {
        T result;
        result = (T) annotation.getClass().getMethod(name).invoke(annotation);
        return result;
    }

    public static <T extends Page> T init(WebDriver driver, Class<T> pageClass)
            throws Exception {
        T page = pageClass.getConstructor(WebDriver.class).newInstance(driver);
        for (Field field : pageClass.getFields()) {
            Annotation anno = field.getAnnotation(FindBy.class);
            if (anno != null) {
                Object control = field
                        .getType()
                        .getConstructor(Page.class, By.class)
                        .newInstance(
                                page,
                                toLocator(getAnnotationField(anno, "locator",
                                        String.class)));
                field.set(page, control);
            }
        }
        return page;
    }
}
