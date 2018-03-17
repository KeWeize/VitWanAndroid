package com.vit.vitwanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author kewz
 * @date 2018/3/18
 */

public class RxClassifyItem implements Serializable {

    private List<RxClassifyItem> children;
    private int courseId;
    private int id;
    private String name;
    private int parentChapterId;
    private int visible;

    public List<RxClassifyItem> getChildren() {
        return children;
    }

    public void setChildren(List<RxClassifyItem> children) {
        this.children = children;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}